package com.indracompany.treinamento.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ContaBancariaDTO implements Serializable {

  private String agencia;
  private String numero;

}
