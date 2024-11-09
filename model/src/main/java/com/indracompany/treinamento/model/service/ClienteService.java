package com.indracompany.treinamento.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.util.xml.Elemento;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository>{
	

	
	/**
	 * Retorna os nomes de todos os clientes
	 * 
	 * @return <! Elemento listaCliente(cliente*)>
	 *         <! Elemento cliente (nome)>
	 */
	public Elemento listarNomeCliente() {
		
		Elemento elementoListaCliente = new Elemento("listaCliente");
		
		List<Cliente> lista = this.listar();
		for (Cliente cliente: lista) {
			Elemento elementoCliente = elementoListaCliente.incluirFilho("cliente");
			elementoCliente.incluirFilho("nome", cliente.getNome());
			
		}
		
		return elementoListaCliente;
		
	}


	  
}
