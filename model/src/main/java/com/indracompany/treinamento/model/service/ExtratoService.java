package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.entity.GenericEntity;
import com.indracompany.treinamento.model.repository.ExtratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtratoService extends GenericCrudService<Extrato, Long, ExtratoRepository > {
    @Autowired
    private ExtratoRepository extratoRepository;

    public List<Extrato> buscarHistorico(String numeroOrigem, String agenciaOrigem){
        if(numeroOrigem == null){
            throw new AplicacaoException(ExceptionValidacoes.ERRO_CAMPO_OBRIGATORIO);
        }
        List<Extrato> extratoList = extratoRepository.findAllByNumeroOrigemAndAgenciaOrigem(numeroOrigem, agenciaOrigem);
        return extratoList;
    }
}


