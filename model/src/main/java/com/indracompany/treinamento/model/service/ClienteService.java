package com.indracompany.treinamento.model.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.util.xml.Elemento;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository> {

	/**
	 * Retorna os dados de todos os clientes
	 *
	 * @return Elemento com a lista de clientes, incluindo nome, agência, conta e saldo
	 */
	public Elemento listarNomeCliente() {

		Elemento elementoListaCliente = new Elemento("listaCliente");

		List<Cliente> lista = this.listar();
		for (Cliente cliente : lista) {
			Elemento elementoCliente = elementoListaCliente.incluirFilho("cliente");
			elementoCliente.setValor("nome", cliente.getNome());
			elementoCliente.setValor("agencia", cliente.getAgencia());
			elementoCliente.setValor("conta", cliente.getConta());
			elementoCliente.setValor("saldo", cliente.getSaldo());
		}

		return elementoListaCliente;
	}
}
