package com.indracompany.treinamento.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.util.xml.Elemento;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository>{
	
    private ContaBancariaRepository contaBancariaRepository;
	
    public ClienteService(ContaBancariaRepository contaBancariaRepository) {
    	this.contaBancariaRepository = contaBancariaRepository;
    }
    
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
	
	public Elemento listarClientInfoBancario() {
		
		 Elemento elementoListaInfoClient = new Elemento("listaInfoClient");
		    List<Cliente> listaClientes = this.listar();
		    
		    for (Cliente cliente : listaClientes) {
		        List<ContaBancaria> contas = contaBancariaRepository.findByClient(cliente);
		        
		        for (ContaBancaria conta : contas) {
		            Elemento elementoClienteInfo = elementoListaInfoClient.incluirFilho("cliente");
		            
		            elementoClienteInfo.incluirFilho("nome", cliente.getNome());
		            elementoClienteInfo.incluirFilho("agencia", conta.getAgencia());
		            elementoClienteInfo.incluirFilho("conta", conta.getNumero());
		            elementoClienteInfo.incluirFilho("saldo", String.valueOf(conta.getSaldo()));
		        }
		    }

	        return elementoListaInfoClient;
		
	}


	  
}
