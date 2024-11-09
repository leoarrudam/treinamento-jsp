package com.indracompany.treinamento.model.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ContaBancariaDTO;
import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.repository.ExtratoRepository;
import com.indracompany.treinamento.util.TipoTransacao;

@Service
public class ExtratoService extends GenericCrudService<Extrato, Long, ExtratoRepository> {
  @Autowired
  private ExtratoRepository extratoRepository;

  public void registrarTrasacao(ContaBancaria conta, TipoTransacao tipo, LocalDateTime dtInicio, LocalDateTime dtFim) {
    Extrato extrato = new Extrato();

    extrato.setTipo(tipo);
    extrato.setConta(conta);
    extrato.setDtInicio(dtInicio);
    extrato.setSaldo(conta.getSaldo());
    extrato.setDtFim(dtFim);

    super.salvar(extrato);
  }

  public List<ExtratoDTO> buscarHistoricoPorConta(ContaBancariaDTO conta) {

    if (conta == null || conta.getNumero() == null || conta.getAgencia() == null) {
      throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);
    }

    String agencia = conta.getAgencia();
    String numero = conta.getNumero();

    List<Extrato> listaExtratoEntity = extratoRepository.findByAgenciaAndConta(agencia, numero);

    List<ExtratoDTO> listaExtratoDto = new ArrayList<>();

    for (Extrato trans : listaExtratoEntity) {
      ExtratoDTO dto = new ExtratoDTO();

      dto.setTipo(trans.getTipo());
      dto.setDtInicio(trans.getDtInicio());
      dto.setDtFim(trans.getDtFim());
      dto.setSaldo(trans.getSaldo());

      listaExtratoDto.add(dto);
    }

    return listaExtratoDto;
  }
}
