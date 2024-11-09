package com.indracompany.treinamento.model.dto;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.util.TipoDeTransacao;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ExtratoDTO implements Serializable {
    private String agencia;
    private String numero;
    private String descricao;
    private TipoDeTransacao tipoDeTransacao;
    private LocalDateTime horario;
    private double valor;
}
