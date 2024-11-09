package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.util.xml.Elemento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{

    @Autowired
    ContaBancariaRepository contaBancariaRepository;
    Extrato extrato;

    @Autowired
    ExtratoService extratoService;

    public Elemento listarTodasContas() {
        List<ContaBancaria> contas = contaBancariaRepository.findAll();
        Elemento listaContas = new Elemento("contas");

        for (ContaBancaria conta : contas) {
            Elemento elementoConta = new Elemento("conta");


            elementoConta.adicionarFilho(new Elemento("clienteNome", conta.getCliente().getNome()));
            elementoConta.adicionarFilho(new Elemento("agencia", conta.getAgencia()));
            elementoConta.adicionarFilho(new Elemento("numero", conta.getNumero()));
            elementoConta.adicionarFilho(new Elemento("saldo", String.valueOf(conta.getSaldo())));


            listaContas.adicionarFilho(elementoConta);
        }

        return listaContas;
    }



    public void depositar(DepositoDTO dto){
        ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
        conta.setSaldo(conta.getSaldo() + dto.getValor());
        super.salvar(conta);

        extrato = new Extrato();

        //Extrato
        extrato.setOperacao("DEPOSITO");
        extrato.setAgenciaOrigem(dto.getAgencia());
        extrato.setNumeroOrigem(dto.getNumeroConta());
        extrato.setValor(dto.getValor());
        extratoService.salvar(extrato);
    }
    public void sacar(SaqueDTO dto) {
        ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
        if (conta.getSaldo() < dto.getValor()){
            throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
        }
        conta.setSaldo(conta.getSaldo() - dto.getValor());
        super.salvar(conta);

        extrato = new Extrato();

        //Extrato
        extrato.setOperacao("SAQUE");
        extrato.setAgenciaOrigem(dto.getAgencia());
        extrato.setNumeroOrigem(dto.getNumeroConta());
        extrato.setValor(dto.getValor());
        extratoService.salvar(extrato);
    }

    public void sacarTransferencia(SaqueDTO dto) {
        ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta());
        if (conta.getSaldo() < dto.getValor()){
            throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
        }
        conta.setSaldo(conta.getSaldo() - dto.getValor());
        super.salvar(conta);
    }

    @Transactional(rollbackFor = Exception.class)
    public void transferir(TransferenciaBancariaDTO transferenciaDto) {
        ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(transferenciaDto.getAgenciaOrigem(), transferenciaDto.getNumeroContaOrigem());

        SaqueDTO saqueDto = new SaqueDTO();
        saqueDto.setAgencia(transferenciaDto.getAgenciaOrigem());
        saqueDto.setNumeroConta(transferenciaDto.getNumeroContaOrigem());
        saqueDto.setValor(transferenciaDto.getValor());
        this.sacarTransferencia(saqueDto);

        DepositoDTO depositoDto = new DepositoDTO();
        depositoDto.setAgencia(transferenciaDto.getAgenciaDestino());
        depositoDto.setNumeroConta(transferenciaDto.getNumeroContaDestino());
        depositoDto.setValor(transferenciaDto.getValor());
        this.depositar(depositoDto);

        // Extrato
        extrato = new Extrato();
        extrato.setOperacao("TRANSFERENCIA");
        extrato.setAgenciaOrigem(transferenciaDto.getAgenciaOrigem());
        extrato.setNumeroOrigem(transferenciaDto.getNumeroContaOrigem());
        extrato.setAgenciaDestino(transferenciaDto.getAgenciaDestino());
        extrato.setNumeroDestino(transferenciaDto.getNumeroContaDestino());
        extrato.setValor(transferenciaDto.getValor());
        extratoService.salvar(extrato);
    }


}