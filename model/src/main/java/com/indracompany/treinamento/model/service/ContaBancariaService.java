package com.indracompany.treinamento.model.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Transacoes;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.model.repository.TransacoesRepository;
import com.indracompany.treinamento.util.xml.Elemento;

@Service
public class ContaBancariaService 
			extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	@Autowired
	private TransacoesRepository transacoesRepository;
	
	public void depositar(DepositoDTO dto) {
		ContaBancaria conta = contaBancariaRepository
				.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
		conta.setSaldo(conta.getSaldo() + dto.getValor());
		super.salvar(conta);
		
		Transacoes transacao = new Transacoes();
		transacao.setConta(conta);
		transacao.setTipoOperacao("DEPÓSITO");
		transacao.setValor(dto.getValor());
		transacao.setDataOperacao(LocalDateTime.now());
	    transacoesRepository.save(transacao);
	}
	
	public void sacar(SaqueDTO dto) {
		ContaBancaria conta = contaBancariaRepository
				.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
		if (conta.getSaldo() < dto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		conta.setSaldo(conta.getSaldo() - dto.getValor());
		super.salvar(conta);
		
		Transacoes transacao = new Transacoes();
		transacao.setConta(conta);
		transacao.setTipoOperacao("SAQUE");
		transacao.setValor(dto.getValor());
		transacao.setDataOperacao(LocalDateTime.now());
	    transacoesRepository.save(transacao);
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
		
		 ContaBancaria contaOrigem = contaBancariaRepository.findByAgenciaAndNumero(transferenciaDto.getAgenciaOrigem(), transferenciaDto.getNumeroContaOrigem());
		 
		 Transacoes transacao = new Transacoes();
		 transacao.setConta(contaOrigem);
		 transacao.setTipoOperacao("TRANSFERÊNCIA");
		 transacao.setValor(transferenciaDto.getValor());
		 transacao.setDataOperacao(LocalDateTime.now());
		 transacao.setContaDestino(transferenciaDto.getAgenciaDestino() + "-" + transferenciaDto.getNumeroContaDestino());
		 transacoesRepository.save(transacao);
	}
	

	public List<Transacoes> consultarTransacoes(String agencia, String numeroConta) {
		ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(agencia, numeroConta);
	    return transacoesRepository.findByConta(conta);
	}
	
	
	 /**
     * Esse método lista todas as contas bancárias e retorna um Elemento representando os dados
     * dos correntistas.
     *  @author matheusv
     *  @return Elemento com a lista de correntistas e informações.
     */
	 public Elemento listarCorrentistasComSaldo() {
	    //    List<ContaBancaria> contas = contaBancariaRepository.findAll();
	        List<ContaBancaria> contas = this.listar();

	        Elemento listaCorrentistas = new Elemento("correntistas");
	        // Para cada conta bancária recuperada criamos um ELemento "correntista"(nome, agência, conta e saldo)
	        for (ContaBancaria conta : contas) {
	        	// Cada elemento individual de um correntista é incluído dentro do Elemento pai "correntistas"
	            Elemento elementoConta = new Elemento("correntista");
	            elementoConta.incluirFilho("nome", conta.getCliente().getNome());
	            elementoConta.incluirFilho("agencia", conta.getAgencia());
	            elementoConta.incluirFilho("conta", conta.getNumero());
	            elementoConta.incluirFilho("saldo", String.format("%.2f", conta.getSaldo()));
	            listaCorrentistas.incluirFilho(elementoConta);
	        }
	    
	        // Aqui retornamos a listaCorrentistas que é um Elemento "correntistas" com vários "correntista" incluídos
	        return listaCorrentistas;
	    }
	
	
	
}