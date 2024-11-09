package com.indracompany.treinamento.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BuscarExtratoDTO  implements Serializable {
    private String agencia;
    private String numero;

}
