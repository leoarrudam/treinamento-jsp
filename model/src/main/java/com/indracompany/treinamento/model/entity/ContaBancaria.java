package com.indracompany.treinamento.model.entity;

import com.indracompany.treinamento.util.xml.Elemento;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Table(name = "conta_bancaria_caua_pires")
@EqualsAndHashCode(callSuper = true)
public class ContaBancaria extends GenericEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4, nullable = false)
    private String agencia;

    @Column(length = 6, nullable = false)
    private String numero;

    @Column(nullable = false)
    private Double saldo;

    //chave estrangeira que faz referência à entidade "Cliente" que possui a conta
    @ManyToOne
    @JoinColumn(name = "nome_cliente")
    private Cliente cliente;

    public ContaBancaria() {}

    public ContaBancaria(String agencia, String numero) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = 0.0;
    }

}
