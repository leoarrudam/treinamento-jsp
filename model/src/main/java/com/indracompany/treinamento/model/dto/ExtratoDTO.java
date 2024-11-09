package com.indracompany.treinamento.model.dto;

import com.indracompany.treinamento.util.TipoDeTransacao;
import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ExtratoDTO implements Serializable {
    private TipoDeTransacao tipoDeTransacao;
    private LocalDateTime horario;
    private double valor;
    private String agencia;
    private String numero;
    private String descricao;
	private String numeroConta;
	private LocalDate dtInicio;
	private LocalDate dtFim;

}
