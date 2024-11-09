package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.model.dto.OperacaoDTO;
import com.indracompany.treinamento.model.entity.Operacao;
import com.indracompany.treinamento.model.repository.OperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OperacaoService extends GenericCrudService<Operacao, Long, OperacaoRepository> {

    @Autowired
    private OperacaoRepository operacaoRepository;

    public void salvar(OperacaoDTO dto) {
        Operacao operacao = new Operacao(dto);
        operacaoRepository.save(operacao);
    }

    public void registrarOperacao(Operacao.TiposOperacao tipoOperacao,
            double valor,
            String agencia,
            String numero) {
        OperacaoDTO operacaoDto = new OperacaoDTO();
        operacaoDto.setTiposOperacao(tipoOperacao);
        operacaoDto.setAgencia(agencia);
        operacaoDto.setNumero(numero);
        operacaoDto.setDataOperacao(LocalDateTime.now());
        operacaoDto.setValor(valor);
        this.salvar(operacaoDto);
    }

}
