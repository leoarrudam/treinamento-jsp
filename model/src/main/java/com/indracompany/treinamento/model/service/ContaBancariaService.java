package com.indracompany.treinamento.model.service;

import java.util.List;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Operacao;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.util.xml.Elemento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository> {

    @Autowired
    ContaBancariaRepository contaBancariaRepository;

    @Autowired
    OperacaoService operacaoService;

    /**
     * Método para listar todas as contas bancárias de clientes
     *
     * @return Lista de contas bancárias formatada em XML
     */
    public Elemento listarContasBancariasPorCliente() {
        Elemento elementoListaContas = new Elemento("listaContas");

        // Lista todas as contas bancárias
        List<ContaBancaria> listaContas = this.listar();
        for (ContaBancaria conta : listaContas) {
            Elemento elementoConta = elementoListaContas.incluirFilho("conta");

            // Adiciona informações do cliente e da conta bancária
            Cliente cliente = conta.getCliente();
            elementoConta.incluirFilho("cliente", cliente != null ? cliente.getNome() : "N/A");
            elementoConta.incluirFilho("agencia", conta.getAgencia());
            elementoConta.incluirFilho("numero", conta.getNumero());
            elementoConta.incluirFilho("saldo", String.valueOf(conta.getSaldo()));
        }

        return elementoListaContas;
    }

    /**
     * Método para realizar um depósito em uma conta bancária
     */
    public void depositar(DepositoDTO dto) {
        ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
        conta.setSaldo(conta.getSaldo() + dto.getValor());
        super.salvar(conta);

        // Registrar operação
        Operacao operacao = new Operacao();
        operacao.setOperacao("DEPOSITO");
        operacao.setAgenciaOrigem(dto.getAgencia());
        operacao.setNumeroOrigem(dto.getNumeroConta());
        operacao.setValor(dto.getValor());
        operacaoService.salvar(operacao);
    }

    /**
     * Método para realizar um saque de uma conta bancária
     */
    public void sacar(SaqueDTO dto) {
        ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
        if (conta.getSaldo() < dto.getValor()) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
        }
        conta.setSaldo(conta.getSaldo() - dto.getValor());
        super.salvar(conta);

        // Registrar operação
        Operacao operacao = new Operacao();
        operacao.setOperacao("SAQUE");
        operacao.setAgenciaOrigem(dto.getAgencia());
        operacao.setNumeroOrigem(dto.getNumeroConta());
        operacao.setValor(dto.getValor());
        operacaoService.salvar(operacao);
    }

    /**
     * Método auxiliar para saque em transferência bancária
     */
    public void sacarTransferencia(SaqueDTO dto) {
        ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
        if (conta.getSaldo() < dto.getValor()) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
        }
        conta.setSaldo(conta.getSaldo() - dto.getValor());
        super.salvar(conta);
    }

    /**
     * Método para realizar uma transferência bancária entre contas
     */
    @Transactional(rollbackFor = Exception.class)
    public void transferir(TransferenciaBancariaDTO transferenciaDto) {
        // Realizar saque da conta de origem
        SaqueDTO saqueDto = new SaqueDTO();
        saqueDto.setAgencia(transferenciaDto.getAgenciaOrigem());
        saqueDto.setNumeroConta(transferenciaDto.getNumeroContaOrigem());
        saqueDto.setValor(transferenciaDto.getValor());
        this.sacarTransferencia(saqueDto);

        // Realizar depósito na conta de destino
        DepositoDTO depositoDto = new DepositoDTO();
        depositoDto.setAgencia(transferenciaDto.getAgenciaDestino());
        depositoDto.setNumeroConta(transferenciaDto.getNumeroContaDestino());
        depositoDto.setValor(transferenciaDto.getValor());
        this.depositar(depositoDto);

        // Registrar operação de transferência
        Operacao operacao = new Operacao();
        operacao.setOperacao("TRANSFERENCIA");
        operacao.setAgenciaOrigem(transferenciaDto.getAgenciaOrigem());
        operacao.setNumeroOrigem(transferenciaDto.getNumeroContaOrigem());
        operacao.setAgenciaDestino(transferenciaDto.getAgenciaDestino());
        operacao.setNumeroDestino(transferenciaDto.getNumeroContaDestino());
        operacao.setValor(transferenciaDto.getValor());
        operacaoService.salvar(operacao);
    }
}
