package com.indracompany.treinamento.model.service;


import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Transacao;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.model.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService extends GenericCrudService<Transacao, Long, TransacaoRepository> {
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    public List<ExtratoDTO> buscarExtratoDTO(String agencia, String numero) {
        ContaBancaria contaBancaria = contaBancariaRepository.findByAgenciaAndNumero(agencia, numero);
        List<Transacao> transacoes = transacaoRepository.findAllByContaBancaria(contaBancaria);
        List<ExtratoDTO> extratoDTOList = new ArrayList<>();
        for (Transacao transacao : transacoes) {
            ExtratoDTO extratoDTO = new ExtratoDTO();
            extratoDTO.setValor(transacao.getValor());
            extratoDTO.setTipoDeTransacao(transacao.getTipoDeTransacao());
            extratoDTO.setHorario(transacao.getHorario());
            extratoDTO.setDescricao(transacao.getDescricao());
            extratoDTO.setAgencia(transacao.getContaBancaria().getAgencia());
            extratoDTO.setNumero(transacao.getContaBancaria().getNumero());
            extratoDTOList.add(extratoDTO);
        }

        return extratoDTOList;
    }

    public void registrarTransacao(ExtratoDTO extratoDTO) {
        ContaBancaria contaBancaria = contaBancariaRepository.findByAgenciaAndNumero(extratoDTO.getAgencia(), extratoDTO.getNumero());
        Transacao transacao = new Transacao();
        transacao.setTipoDeTransacao(extratoDTO.getTipoDeTransacao());
        transacao.setValor(extratoDTO.getValor());
        transacao.setHorario(LocalDateTime.now());
        transacao.setDescricao(extratoDTO.getDescricao());
        transacao.setContaBancaria(contaBancaria);

        super.salvar(transacao);
    }
}