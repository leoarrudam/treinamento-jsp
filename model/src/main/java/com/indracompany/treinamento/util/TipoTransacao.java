package com.indracompany.treinamento.util;

public enum TipoTransacao {
  SAQUE("saque"),
  DEPOSITO("deposito"),
  TRANSFERENCIA("transferencia");

  private final String tipo;

  TipoTransacao(String tipo) {
    this.tipo = tipo;
  }

  public String getTipo() {
    return tipo;
  }
}
