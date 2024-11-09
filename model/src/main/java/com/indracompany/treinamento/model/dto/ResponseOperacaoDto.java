package com.indracompany.treinamento.model.dto;

import com.indracompany.treinamento.model.entity.Operacao;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ResponseOperacaoDto {
    private double valor;
    private Operacao.TiposOperacao tipoOperacao;
    private LocalDateTime dataOperacao;
}
