package com.indracompany.treinamento.controller.rest;

import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/contas")
public class ContaBancariaRest extends GenericCrudRest<ContaBancaria, Long, ContaBancariaService> {
    @Autowired
    private ContaBancariaService contaBancariaService;

    @PutMapping(value = "/deposito", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Void> depositar(@RequestBody DepositoDTO dto){
        contaBancariaService.depositar(dto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping(value = "/saque", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Void> sacar(@RequestBody SaqueDTO dto){
        contaBancariaService.sacar(dto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping(value = "/transferir", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Void> transferir(@RequestBody TransferenciaBancariaDTO dto){
        contaBancariaService.transferir(dto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}