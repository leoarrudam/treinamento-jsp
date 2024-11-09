package com.indracompany.treinamento.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.util.xml.Elemento;


@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	
	
	public Elemento listarCorrentistas() {
		
		Elemento elementoListaCorrentistas = new Elemento("listaCorrentistas");
		
		List<ContaBancaria> lista = this.listar();
		for (ContaBancaria contaBancaria: lista) {
			Elemento elementoCliente = elementoListaCorrentistas.incluirFilho("cliente");
			
			String nomeCorrentista = contaBancaria.getCliente().getNome();
			
			elementoCliente.incluirFilho("nome", nomeCorrentista);
	        elementoCliente.incluirFilho("agencia", contaBancaria.getAgencia());
	        elementoCliente.incluirFilho("conta", contaBancaria.getNumero());
	        elementoCliente.incluirFilho("saldo", String.valueOf(contaBancaria.getSaldo()));
		}
		
		return elementoListaCorrentistas;
		
	}
}
