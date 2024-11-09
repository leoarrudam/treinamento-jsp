package com.indracompany.treinamento.model.dto;

import java.time.LocalDateTime;

import com.indracompany.treinamento.util.TipoTransacao;

import lombok.Data;

@Data
public class ExtratoDTO {

  private TipoTransacao tipo; // "SAQUE", "DEPOSITO", "TRANSFERENCIA"

  private LocalDateTime dtInicio;
  
  private LocalDateTime dtFim;

  private double saldo;

}
