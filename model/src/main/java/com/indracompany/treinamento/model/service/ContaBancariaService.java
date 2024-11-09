package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.util.xml.Elemento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository> {

    @Autowired
    public ContaBancariaRepository contaBancariaRepository;

    //acrescenta x do saldo no banco de dados
    public void depositar(DepositoDTO dto) {
        ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
        conta.setSaldo(conta.getSaldo() + dto.getValor());
        super.salvar(conta);
    }

    //subtrai x do saldo no banco de dados
    public void sacar(SaqueDTO dto) {
        ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());

        //realizando verificação do saldo antes de realizar o saque
        if (conta.getSaldo() < dto.getValor()) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
        }
        conta.setSaldo(conta.getSaldo() - dto.getValor());
        super.salvar(conta);
    }

    //retorna um elemento que é uma lista de contasbancárias
    public Elemento listarContasBancarias() {

        Elemento elementoListaContasBancarias = new Elemento("listaContasBancarias");

        List<ContaBancaria> listaContas = this.listar();

        for (ContaBancaria conta: listaContas) {

            //elemento conta obtido da iteração da lista de elementos "ContaBancaria" (this.listar())
            Elemento elementoContaBancaria = elementoListaContasBancarias.incluirFilho("conta");

            //campos de cada conta bancária
            elementoContaBancaria.incluirFilho("nome_cliente", conta.getCliente().getNome());
            elementoContaBancaria.incluirFilho("agencia", conta.getAgencia());
            elementoContaBancaria.incluirFilho("conta", conta.getNumero());
            elementoContaBancaria.incluirFilho("saldo", conta.getSaldo());
        }

        return elementoListaContasBancarias;

    }
}
