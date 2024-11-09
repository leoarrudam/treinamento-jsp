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
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ContaBancariaRepository ContaBancariaRepository;
	
	
	
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
			elementoCliente.incluirFilho("cpf", cliente.getCpf());
			elementoCliente.incluirFilho("email", cliente.getEmail());
			
		}
		
		return elementoListaCliente;
		
	}
	
	public Elemento listarClientesEContas() {
           
		Elemento elementoListaClienteEConta = new Elemento("listaClienteEConta");
		
		List<Cliente> lista = this.listar();
		
		for (Cliente cliente : lista) {
			
			List<ContaBancaria> contas = ContaBancariaRepository.findByClienteCpf(cliente.getCpf());
			
		for (ContaBancaria conta: contas) {
			
			Elemento elementoCliente = elementoListaClienteEConta.incluirFilho("cliente");
			elementoCliente.incluirFilho("nome", cliente.getNome());
			elementoCliente.incluirFilho("agencia", conta.getAgencia());
			elementoCliente.incluirFilho("conta", conta.getNumero());
			elementoCliente.incluirFilho("saldo", conta.getSaldo());
		}
		}
		
        return elementoListaClienteEConta;
	}
	


	  
}
