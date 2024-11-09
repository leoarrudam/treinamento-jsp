package com.indracompany.treinamento.model.dto;

import com.indracompany.treinamento.model.entity.enums.OperacaoTipo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperacaoDTO {

    private Long id;
    private Double valor;
    private String descricao;
    private LocalDateTime date;
    private OperacaoTipo tipo;
}
