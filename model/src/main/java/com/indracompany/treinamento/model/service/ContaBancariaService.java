package com.indracompany.treinamento.model.service;

import java.util.List;

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
import com.indracompany.treinamento.util.xml.Elemento;

@Service
public class ContaBancariaService 
			extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
		
	public void depositar(DepositoDTO dto) {
		ContaBancaria conta = contaBancariaRepository
				.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
		conta.setSaldo(conta.getSaldo() + dto.getValor());
		super.salvar(conta);
	}
	
	public void sacar(SaqueDTO dto) {
		ContaBancaria conta = contaBancariaRepository
				.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
		if (conta.getSaldo() < dto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		conta.setSaldo(conta.getSaldo() - dto.getValor());
		super.salvar(conta);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO transferenciaDto) {
		ContaBancaria contaOrigem = contaBancariaRepository
				.findByAgenciaAndNumero(transferenciaDto.getAgenciaOrigem(), transferenciaDto.getNumeroContaOrigem());
		SaqueDTO saqueDto = new SaqueDTO();
		saqueDto.setAgencia(transferenciaDto.getAgenciaOrigem());
		saqueDto.setNumeroConta(transferenciaDto.getNumeroContaOrigem());
		saqueDto.setValor(transferenciaDto.getValor());
		if (contaOrigem.getSaldo() < transferenciaDto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		contaOrigem.setSaldo(contaOrigem.getSaldo() - transferenciaDto.getValor());
		super.salvar(contaOrigem);
		
		ContaBancaria contaDestino = contaBancariaRepository
				.findByAgenciaAndNumero(transferenciaDto.getAgenciaDestino(), transferenciaDto.getNumeroContaDestino());
		DepositoDTO depositoDto = new DepositoDTO();
		depositoDto.setAgencia(transferenciaDto.getAgenciaDestino());
		depositoDto.setNumeroConta(transferenciaDto.getNumeroContaDestino());
		depositoDto.setValor(transferenciaDto.getValor());
		contaDestino.setSaldo(contaDestino.getSaldo() + transferenciaDto.getValor());
		super.salvar(contaDestino);
	}
	
	/**
     * Retorna uma lista de contas bancárias com o nome do cliente, agência, número da conta e saldo.
     *
     * @return <! Elemento listaContasBancarias(conta*)>
     *         <! Elemento conta (nome, agencia, numero, saldo)>
     */
	
	public Elemento listarDetalhesDasContas() {
		
		Elemento elementoListaDeContasComDetalhes = new Elemento("listaContasBancarias");
		List<ContaBancaria> contas = this.listar();
	       for (ContaBancaria conta : contas) {
	    	   Elemento elementoConta = new Elemento("conta");
	           elementoConta.incluirFilho(new Elemento("nome", conta.getCliente().getNome()));
	           elementoConta.incluirFilho(new Elemento("agencia", conta.getAgencia()));
	           elementoConta.incluirFilho(new Elemento("numero", conta.getNumero()));
	           elementoConta.incluirFilho(new Elemento("saldo", String.valueOf(conta.getSaldo())));
	           
	           elementoListaDeContasComDetalhes.incluirFilho(elementoConta);
	       }		
		
		return elementoListaDeContasComDetalhes;
	}
}