package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Transacao;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.model.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService extends GenericCrudService<Transacao, Long, TransacaoRepository>{
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    public List<ExtratoDTO> buscarExtrato(String agencia, String numero){
        ContaBancaria contaBancaria = contaBancariaRepository.findByAgenciaAndNumero(agencia, numero);
        System.out.println(contaBancaria);
        List<Transacao> transacoes = transacaoRepository.findAllByContaBancaria(contaBancaria);
        System.out.println(transacoes);

        List<ExtratoDTO> extratoDTOList = new ArrayList<>();

        for (Transacao transacao : transacoes) {
            ExtratoDTO extratoDTO = new ExtratoDTO();
            extratoDTO.setTipoDeTransacao(transacao.getTipoDeTransacao());
            extratoDTO.setValor(transacao.getValor());
            extratoDTO.setHorario(transacao.getHorario());
            extratoDTO.setDescricao(transacao.getDescricao());
            extratoDTO.setAgencia(transacao.getContaBancaria().getAgencia());
            extratoDTO.setNumero(transacao.getContaBancaria().getNumero());
            extratoDTOList.add(extratoDTO);
        }

        System.out.println(extratoDTOList);

        return extratoDTOList;
    }

    public void registrarTransacao(ExtratoDTO extratoDTO){
        ContaBancaria contaBancaria = contaBancariaRepository.findByAgenciaAndNumero(extratoDTO.getAgencia(), extratoDTO.getNumero());

        Transacao transacao = new Transacao();
        transacao.setValor(extratoDTO.getValor());
        transacao.setDescricao(extratoDTO.getDescricao());
        transacao.setContaBancaria(contaBancaria);
        transacao.setHorario(LocalDateTime.now());
        transacao.setTipoDeTransacao(extratoDTO.getTipoDeTransacao());

        super.salvar(transacao);
    }
}
