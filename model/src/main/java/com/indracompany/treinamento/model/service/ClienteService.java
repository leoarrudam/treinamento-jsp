package com.indracompany.treinamento.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.util.xml.Elemento;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository>{
	
//	private ContaBancariaService contaBancariaService = new ContaBancariaService();
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
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

	public Elemento listarAtributosClientes() {
		
		Elemento elementoListaCliente = new Elemento("listaCliente");
		
		List<Cliente> lista = this.listar();
		for (Cliente cliente: lista) {
			
			if (cliente.getNome() != null && !cliente.getNome().equals("string")) {
				
				Elemento elementoCliente = elementoListaCliente.incluirFilho("cliente");
				elementoCliente.incluirFilho("nome", cliente.getNome());
				
				List<ContaBancaria> listaContaBancariaCliente = contaBancariaRepository.findByClienteCpf(cliente.getCpf());
				
				Elemento elementoListaContaBancaria = new Elemento("listaContaBancaria");
				
				for (ContaBancaria contaBancaria: listaContaBancariaCliente) {
					
					Elemento elementoContaBancaria = elementoListaContaBancaria.incluirFilho("contaBancaria");
					elementoContaBancaria.incluirFilho("agencia", contaBancaria.getAgencia());
					elementoContaBancaria.incluirFilho("conta", contaBancaria.getNumero());
					elementoContaBancaria.incluirFilho("saldo", contaBancaria.getSaldo());
				}
				elementoCliente.incluirFilho(elementoListaContaBancaria);
			}
		}
		
		return elementoListaCliente;
	}
	  
}
