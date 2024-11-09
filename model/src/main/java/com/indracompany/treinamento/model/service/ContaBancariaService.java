package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.model.dto.*;
import com.indracompany.treinamento.model.entity.Operacao;
import com.indracompany.treinamento.model.repository.OperacaoRepository;
import com.indracompany.treinamento.util.xml.Elemento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContaBancariaService 
			extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;

	@Autowired
	private OperacaoService operacaoService;

	@Autowired
	private OperacaoRepository operacaoRepository;

	public Elemento listarContaBancaria() {
		Elemento elementoListaContaBancaria = new Elemento("listaContaBancaria");
		List<ContaBancaria> lista = super.listar();

		for (ContaBancaria conta: lista) {
			Elemento elementoContaBancaria = new Elemento("contaBancaria");
			elementoContaBancaria.incluirFilho("nome",conta.getCliente().getNome());
			elementoContaBancaria.incluirFilho("agencia",conta.getAgencia());
			elementoContaBancaria.incluirFilho("numeroConta",conta.getNumero());
			elementoContaBancaria.incluirFilho("saldo", conta.getSaldo());
			elementoListaContaBancaria.incluirFilho(elementoContaBancaria);
		}
		return elementoListaContaBancaria;
	}

	public void depositar(DepositoDTO depositoDto) {
		ContaBancaria conta = contaBancariaRepository
				.findByAgenciaAndNumero(depositoDto.getAgencia(), depositoDto.getNumeroConta());
		conta.setSaldo(conta.getSaldo() + depositoDto.getValor());
		super.salvar(conta);
		operacaoService.registrarOperacao(Operacao.TiposOperacao.DEPOSITO,
				depositoDto.getValor(),
				depositoDto.getAgencia(),
				depositoDto.getNumeroConta());
	}

	public void sacar(SaqueDTO saqueDto) {
		ContaBancaria conta = contaBancariaRepository
				.findByAgenciaAndNumero(saqueDto.getAgencia(), saqueDto.getNumeroConta());
		if (conta.getSaldo() < saqueDto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		conta.setSaldo(conta.getSaldo() - saqueDto.getValor());
		super.salvar(conta);
		operacaoService.registrarOperacao(Operacao.TiposOperacao.SAQUE,
				saqueDto.getValor(),
				saqueDto.getAgencia(),
				saqueDto.getNumeroConta());
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

	public List<ResponseOperacaoDto> mostrarExtrato(ExtratoDTO dto){
		List<ResponseOperacaoDto> response = new ArrayList<>();
		List<Operacao> operacoes = operacaoRepository.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
		for (Operacao op: operacoes) {
			ResponseOperacaoDto responseDto = new ResponseOperacaoDto();
			responseDto.setDataOperacao(op.getData_operacao());
			responseDto.setTipoOperacao(op.getTipo_operacao());
			responseDto.setValor(op.getValor());
			response.add(responseDto);
		}
		return response;
	}
}
