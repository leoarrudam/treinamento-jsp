package com.indracompany.treinamento.model.dto;

import com.indracompany.treinamento.model.entity.Operacao;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class OperacaoDTO implements Serializable {

    private String agencia;

    private String numero;

    private Operacao.TiposOperacao tiposOperacao;

    private double valor;

    private LocalDateTime dataOperacao;
}
