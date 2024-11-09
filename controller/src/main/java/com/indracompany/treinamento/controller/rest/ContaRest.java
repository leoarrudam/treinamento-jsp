package com.indracompany.treinamento.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.MediaType;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.entity.Conta;
import com.indracompany.treinamento.model.service.ContaService;
import com.indracompany.treinamento.model.service.TransferenciaDTO;

@RestController
@RequestMapping("rest/contas")
public class ContaRest extends GenericCrudRest<Conta, Long, ContaService> {

    @Autowired
    private ContaService contaService;

    /**
     * Endpoint para realizar um depósito na conta bancária.
     * @param dto Dados do depósito a ser realizado.
     * @return ResponseEntity com status HTTP 200.
     */
    @PutMapping(value = "/depositar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> depositar(@RequestBody DepositoDTO dto) {
        contaService.depositar(dto);
        return ResponseEntity.ok().build(); // Simplificando a criação do ResponseEntity
    }

    /**
     * Endpoint para realizar um saque da conta bancária.
     * @param dto Dados do saque a ser realizado.
     * @return ResponseEntity com status HTTP 200.
     */
    @PutMapping(value = "/sacar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sacar(@RequestBody SaqueDTO dto) {
        contaService.sacar(dto);
        return ResponseEntity.ok().build(); // Simplificando a criação do ResponseEntity
    }

    /**
     * Endpoint para realizar uma transferência bancária.
     * @param dto Dados da transferência a ser realizada.
     * @return ResponseEntity com status HTTP 200.
     */
    @PutMapping(value = "/transferir", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> transferir(@RequestBody TransferenciaDTO dto) {
        contaService.transferir(dto);
        return ResponseEntity.ok().build(); 
    }
}