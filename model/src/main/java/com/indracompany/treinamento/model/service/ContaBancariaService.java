package com.indracompany.treinamento.model.service;

import java.time.LocalDateTime;

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
import com.indracompany.treinamento.util.TipoTransacao;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository> {

  @Autowired
  private ContaBancariaRepository contaBancariaRepository;

  @Autowired
  private ExtratoService extratoService;

  public void depositar(DepositoDTO dto, boolean reg) {
    ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());

    LocalDateTime dtInicio = LocalDateTime.now();

    conta.setSaldo(conta.getSaldo() + dto.getValor());

    LocalDateTime dtFim = LocalDateTime.now();

    if (reg) {
      extratoService.registrarTrasacao(conta, TipoTransacao.DEPOSITO, dtInicio, dtFim);
    }

    super.salvar(conta);
  }

  public void sacar(SaqueDTO dto, boolean reg) {
    ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());

    if (conta.getSaldo() < dto.getValor()) {
      throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
    }

    LocalDateTime dtInicio = LocalDateTime.now();

    conta.setSaldo(conta.getSaldo() - dto.getValor());

    LocalDateTime dtFim = LocalDateTime.now();

    if (reg) {
      extratoService.registrarTrasacao(conta, TipoTransacao.SAQUE, dtInicio, dtFim);
    }

    super.salvar(conta);
  }

  @Transactional(rollbackFor = Exception.class)
  public void transferir(TransferenciaBancariaDTO dto) {
    ContaBancaria contaOrigem = contaBancariaRepository.findByAgenciaAndNumero(dto.getAgenciaOrigem(),
        dto.getNumeroContaOrigem());

    ContaBancaria contaDestino = contaBancariaRepository.findByAgenciaAndNumero(dto.getAgenciaDestino(),
        dto.getNumeroContaDestino());

    LocalDateTime dtInicioOrigem = LocalDateTime.now();

    SaqueDTO saqueDTO = new SaqueDTO();

    saqueDTO.setAgencia(dto.getAgenciaOrigem());
    saqueDTO.setNumeroConta(dto.getNumeroContaOrigem());
    saqueDTO.setValor(dto.getValor());

    this.sacar(saqueDTO, false);

    DepositoDTO depositoDTO = new DepositoDTO();

    depositoDTO.setAgencia(dto.getAgenciaDestino());
    depositoDTO.setNumeroConta(dto.getNumeroContaDestino());
    depositoDTO.setValor(dto.getValor());

    this.depositar(depositoDTO, false);

    LocalDateTime dtFimDestino = LocalDateTime.now();

    extratoService.registrarTrasacao(contaOrigem, TipoTransacao.TRANSFERENCIA, dtInicioOrigem, dtFimDestino);
    extratoService.registrarTrasacao(contaDestino, TipoTransacao.TRANSFERENCIA, dtInicioOrigem, dtFimDestino);
  }
}
