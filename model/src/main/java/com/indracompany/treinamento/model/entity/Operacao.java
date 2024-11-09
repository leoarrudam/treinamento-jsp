package com.indracompany.treinamento.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "OPERACAORaafa81AnddxArtur")
@Data
@EqualsAndHashCode(callSuper = true)
public class Operacao extends GenericEntity<Long>{
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column
	    private String operacao;
	    
	    @Column(length = 4)
	    private String agenciaOrigem;
	    @Column(length = 6)
	    private String numeroOrigem;
	    @Column
	    private double valor;

	  
	    @Column(length = 4)
	    private String agenciaDestino;
	    @Column(length = 6)
	    private String numeroDestino;
	}
