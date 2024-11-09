package com.indracompany.treinamento.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.util.xml.Elemento;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository> {

    @Autowired
    private ContaBancariaService contaBancariaService;

    /**
     * Retorna os nomes de todos os clientes com seus respectivos saldos,
     * agência e número da conta.
     *
     * @return <! Elemento listaCliente(cliente*)>
     *         <! Elemento cliente (nome, agencia, numeroConta, saldo)>
     */
    public Elemento listarClientesComSaldo() {
        Elemento elementoListaClientes = new Elemento("listaCliente");
        
        List<Cliente> listaClientes = this.listar();
        for (Cliente cliente : listaClientes) {
            // Para cada cliente, obtenha as contas bancárias
            List<ContaBancaria> contas = contaBancariaService.listarContasPorCliente(cliente);

            for (ContaBancaria conta : contas) {
                Elemento elementoCliente = elementoListaClientes.incluirFilho("cliente");
                elementoCliente.incluirFilho("nome", cliente.getNome());
                elementoCliente.incluirFilho("agencia", conta.getAgencia());
                elementoCliente.incluirFilho("numero", conta.getNumero());
                elementoCliente.incluirFilho("saldo", conta.getSaldo());
            }
        }
        
        return elementoListaClientes;
    }
}
