package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.model.dto.*;
import com.indracompany.treinamento.model.entity.Operacao;
import com.indracompany.treinamento.model.repository.OperacaoRepository;
import com.indracompany.treinamento.util.xml.Elemento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContaBancariaService
        extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository> {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Autowired
    private OperacaoService operacaoService;

    @Autowired
    private OperacaoRepository operacaoRepository;

    /**
     * Retorna todas as contas bancarias
     * 
     * @return <! Elemento listaContaBancaria(contaBancaria*)>
     *         <! Elemento contaBancaria (nome,agencia,numero,saldo)>
     */
    public Elemento listarContaBancaria() {

        Elemento elementoListaContaBancaria = new Elemento("listaContaBancaria");

        List<ContaBancaria> listaContaBancaria = this.listar();
        for (ContaBancaria contaBancaria : listaContaBancaria) {
            Elemento elementoContaBancaria = elementoListaContaBancaria.incluirFilho("contaBancaria");
            elementoContaBancaria.incluirFilho("nome", contaBancaria.getCliente().getNome());
            elementoContaBancaria.incluirFilho("agencia", contaBancaria.getAgencia());
            elementoContaBancaria.incluirFilho("numero", contaBancaria.getNumero());
            elementoContaBancaria.incluirFilho("saldo", contaBancaria.getSaldo());
        }

        return elementoListaContaBancaria;

    }

    public void depositar(DepositoDTO depositoDto) {
        ContaBancaria conta = contaBancariaRepository
                .findByAgenciaAndNumero(depositoDto.getAgencia(), depositoDto.getNumeroConta());
        conta.setSaldo(conta.getSaldo() + depositoDto.getValor());
        super.salvar(conta);
        operacaoService.registrarOperacao(Operacao.TiposOperacao.DEPOSITO,
                depositoDto.getValor(),
                depositoDto.getAgencia(),
                depositoDto.getNumeroConta());
    }

    public void sacar(SaqueDTO saqueDto) {
        ContaBancaria conta = contaBancariaRepository
                .findByAgenciaAndNumero(saqueDto.getAgencia(), saqueDto.getNumeroConta());
        if (conta.getSaldo() < saqueDto.getValor()) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
        }
        conta.setSaldo(conta.getSaldo() - saqueDto.getValor());
        super.salvar(conta);
        operacaoService.registrarOperacao(Operacao.TiposOperacao.SAQUE,
                saqueDto.getValor(),
                saqueDto.getAgencia(),
                saqueDto.getNumeroConta());
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

    public List<ResponseOperacaoDTO> mostrarExtrato(ExtratoDTO dto) {
        List<ResponseOperacaoDTO> response = new ArrayList<>();
        List<Operacao> operacoes = operacaoRepository.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
        for (Operacao op : operacoes) {
            ResponseOperacaoDTO responseDto = new ResponseOperacaoDTO();
            responseDto.setDataOperacao(op.getData_operacao());
            responseDto.setTipoOperacao(op.getTipo_operacao());
            responseDto.setValor(op.getValor());
            response.add(responseDto);
        }
        return response;
    }
}
