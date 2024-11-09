package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.entity.Operacao;
import com.indracompany.treinamento.model.repository.OperacaoRepository;
import com.indracompany.treinamento.model.entity.GenericEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperacaoService extends GenericCrudService<Operacao, Long, OperacaoRepository > {
    @Autowired
    private OperacaoRepository operacaoRepository;

    public List<Operacao> buscarHistorico(String numeroOrigem, String agenciaOrigem){
        if(numeroOrigem == null){
            throw new AplicacaoException(ExceptionValidacoes.ERRO_CAMPO_OBRIGATORIO);
        }
        List<Operacao> extratoList = operacaoRepository.findAllByNumeroOrigemAndAgenciaOrigem(numeroOrigem, agenciaOrigem);
        return extratoList;
    }
} 