package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.enums.OperacaoTipo;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContaBancariaService 
			extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	@Autowired
	private OperacaoService operacaoService;
	
	public void depositar(DepositoDTO dto) {
		ContaBancaria conta = contaBancariaRepository
				.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
		conta.setSaldo(conta.getSaldo() + dto.getValor());
		super.salvar(conta);

		operacaoService.saveOperacao(dto.getValor(), conta, "Depósito", OperacaoTipo.ENTRADA);
	}
	
	public void sacar(SaqueDTO dto) {
		ContaBancaria conta = contaBancariaRepository
				.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
		if (conta.getSaldo() < dto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		conta.setSaldo(conta.getSaldo() - dto.getValor());
		super.salvar(conta);

		operacaoService.saveOperacao(dto.getValor(), conta, "Saque", OperacaoTipo.SAIDA);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO transferenciaDto) {
		ContaBancaria conta = contaBancariaRepository
				.findByAgenciaAndNumero(transferenciaDto.getAgenciaOrigem(), transferenciaDto.getNumeroContaOrigem());
		if (conta.getSaldo() < transferenciaDto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		conta.setSaldo(conta.getSaldo() - transferenciaDto.getValor());
		super.salvar(conta);
		operacaoService.saveOperacao(
				transferenciaDto.getValor(),
				conta,
				"Enviado para a agencia: " + transferenciaDto.getAgenciaDestino() + " numero: " + transferenciaDto.getNumeroContaDestino(),
				OperacaoTipo.SAIDA);

		conta = contaBancariaRepository
				.findByAgenciaAndNumero(transferenciaDto.getAgenciaDestino(), transferenciaDto.getNumeroContaDestino());
		conta.setSaldo(conta.getSaldo() + transferenciaDto.getValor());
		super.salvar(conta);

		operacaoService.saveOperacao(
				transferenciaDto.getValor(),
				conta,
				"Recebido da agencia: " + transferenciaDto.getAgenciaOrigem() + " numero: " + transferenciaDto.getNumeroContaOrigem(),
				OperacaoTipo.ENTRADA);
	}
}