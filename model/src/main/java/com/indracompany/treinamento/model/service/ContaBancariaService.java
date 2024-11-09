package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.util.TipoDeTransacao;
import com.indracompany.treinamento.util.xml.Elemento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;

import java.util.List;

@Service
public class ContaBancariaService 
			extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	@Autowired
	private TransacaoService transacaoService;
	
	public void depositar(DepositoDTO dto) {
		ContaBancaria conta = contaBancariaRepository
				.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
		conta.setSaldo(conta.getSaldo() + dto.getValor());
		super.salvar(conta);

		gerarExtrato(conta, TipoDeTransacao.Deposito, dto.getValor(), "Depósito realizado");
	}
	
	public void sacar(SaqueDTO dto) {
		ContaBancaria conta = contaBancariaRepository
				.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
		if (conta.getSaldo() < dto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		conta.setSaldo(conta.getSaldo() - dto.getValor());
		super.salvar(conta);

		gerarExtrato(conta, TipoDeTransacao.Saque, dto.getValor(), "Saque realizado");
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO transferenciaDto) {

		SaqueDTO saqueDto = new SaqueDTO();
		saqueDto.setAgencia(transferenciaDto.getAgenciaOrigem());
		saqueDto.setNumeroConta(transferenciaDto.getNumeroContaOrigem());
		saqueDto.setValor(transferenciaDto.getValor());
		this.sacar(saqueDto);
		
		DepositoDTO depositoDto = new DepositoDTO();
		depositoDto.setAgencia(transferenciaDto.getAgenciaDestino());
		depositoDto.setNumeroConta(transferenciaDto.getNumeroContaDestino());
		depositoDto.setValor(transferenciaDto.getValor());
		this.depositar(depositoDto);
		
	}

	public void gerarExtrato(ContaBancaria contaBancaria, TipoDeTransacao tipoDeTransacao, double valor, String descricao){
		ExtratoDTO extratoDTO = new ExtratoDTO();

		System.out.println("1"+ contaBancaria);
		extratoDTO.setNumero(contaBancaria.getNumero());
		extratoDTO.setAgencia(contaBancaria.getAgencia());
		extratoDTO.setDescricao(descricao);
		extratoDTO.setTipoDeTransacao(tipoDeTransacao);
		extratoDTO.setValor(valor);

		System.out.println("2"+extratoDTO);

		transacaoService.registrarTransacao(extratoDTO);

	}

	public Elemento listarContasBancarias() {

		Elemento elementoListaContaBancaria = new Elemento("listaContaBancaria");

		List<ContaBancaria> lista = this.listar();
		for (ContaBancaria contaBancaria: lista) {
			Elemento elementoContaBancaria = elementoListaContaBancaria.incluirFilho("contaBancaria");
			elementoContaBancaria.incluirFilho("nome", contaBancaria.getCliente().getNome());
			elementoContaBancaria.incluirFilho("agencia", contaBancaria.getAgencia());
			elementoContaBancaria.incluirFilho("conta", contaBancaria.getNumero());
			elementoContaBancaria.incluirFilho("saldo", contaBancaria.getSaldo());
		}

		return elementoListaContaBancaria;

	}
}
