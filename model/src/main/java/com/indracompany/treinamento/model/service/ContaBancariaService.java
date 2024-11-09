package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.util.xml.Elemento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;

import java.util.List;

@Service
public class ContaBancariaService
        extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository> {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    /**
     * Retorna todas as contas e seus dados
     *
     * @return <! Elemento listaContasBancarias(conta*)>
     * <! Elemento conta (nome, agencia, numero, saldo)>
     */
    public Elemento listarContasBancarias() {
        Elemento elementoListaContasBancarias = new Elemento("listaContasBancarias");
        List<ContaBancaria> lista = this.listar();

        for (ContaBancaria conta : lista) {
            Elemento elementoConta = elementoListaContasBancarias.incluirFilho("conta");
            elementoConta.incluirFilho("nome", conta.getCliente().getNome());
            elementoConta.incluirFilho("agencia", conta.getAgencia());
            elementoConta.incluirFilho("numero", conta.getNumero());
            elementoConta.incluirFilho("saldo", conta.getSaldo());
        }

        return elementoListaContasBancarias;
    }

    public Elemento buscaContaPorNome(String nome) {
        Elemento elementoConta = new Elemento("contaBancaria");
        ContaBancaria conta = contaBancariaRepository.findByClienteNomeContainsIgnoreCase(nome);
        if (conta != null) {
            elementoConta.incluirFilho("nome", conta.getCliente().getNome());
            elementoConta.incluirFilho("agencia", conta.getAgencia());
            elementoConta.incluirFilho("numero", conta.getNumero());
            elementoConta.incluirFilho("saldo", conta.getSaldo());

        } else {
            elementoConta = null;
        }
        return elementoConta;
    }

    public void depositar(DepositoDTO dto) {
        ContaBancaria conta = contaBancariaRepository
                .findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
        conta.setSaldo(conta.getSaldo() + dto.getValor());
        super.salvar(conta);
    }

    public void sacar(SaqueDTO dto) {
        ContaBancaria conta = contaBancariaRepository
                .findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
        if (conta.getSaldo() < dto.getValor()) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
        }
        conta.setSaldo(conta.getSaldo() - dto.getValor());
        super.salvar(conta);
    }

    @Transactional(rollbackFor = Exception.class)
    public void transferir(TransferenciaBancariaDTO transferenciaDto) {
        SaqueDTO saqueDto = new SaqueDTO();
        saqueDto.setAgencia(transferenciaDto.getAgenciaOrigem());
        saqueDto.setNumeroConta(transferenciaDto.getNumeroContaOrigem());
        saqueDto.setValor(transferenciaDto.getValor());
        this.sacar(saqueDto);

        DepositoDTO depositoDto = new DepositoDTO();
        depositoDto.setAgencia(transferenciaDto.getAgenciaDestino());
        depositoDto.setNumeroConta(transferenciaDto.getNumeroContaDestino());
        depositoDto.setValor(transferenciaDto.getValor());
        this.depositar(depositoDto);

    }
}
