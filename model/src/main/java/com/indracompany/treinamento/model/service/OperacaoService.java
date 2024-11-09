package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.model.dto.OperacaoDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Operacao;
import com.indracompany.treinamento.model.entity.enums.OperacaoTipo;
import com.indracompany.treinamento.model.repository.OperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperacaoService extends GenericCrudService<Operacao, Long, OperacaoRepository> {

    @Autowired
    private OperacaoRepository operacaoRepository;

    public List<OperacaoDTO> buscarPorAgenciaENumeroDaConta(String agencia, String numero) {
        List<Operacao> operacaos = operacaoRepository.findAllByContaBancaria_AgenciaAndContaBancaria_Numero(agencia, numero);

        return operacaos.stream().map(this::convertOperacaoToOperacaoDTO).collect(Collectors.toList());
    }

    private OperacaoDTO convertOperacaoToOperacaoDTO(Operacao operacao) {
        OperacaoDTO operacaoDTO = new OperacaoDTO();

        operacaoDTO.setId(operacao.getId());
        operacaoDTO.setValor(operacao.getValor());
        operacaoDTO.setDescricao(operacao.getDescricao());
        operacaoDTO.setDate(operacao.getDate());
        operacaoDTO.setTipo(operacao.getTipo());

        return operacaoDTO;
    }

    public void saveOperacao(double valor, ContaBancaria contaBancaria, String descricao, OperacaoTipo operacaoTipo) {
        Operacao operacao = new Operacao();
        operacao.setValor(valor);
        operacao.setDescricao(descricao);
        operacao.setDate(LocalDateTime.now());
        operacao.setTipo(operacaoTipo);
        operacao.setContaBancaria(contaBancaria);

        super.salvar(operacao);
    }
}