package com.indracompany.treinamento.util.ContaBancaria;

import com.indracompany.treinamento.util.xml.Elemento;

public class ContaBancariaUtil {
    public static String  imprimirTuplaHtml(Elemento conta) {
        //retornando os valores como tupla de uma tabela HTML
        return String.format("<td> %s </td> " +
                "<td> %s </td> " +
                "<td> %s </td> " +
                "<td> %s </td>", conta.getFilho("nome_cliente"), conta.getFilho("agencia"), conta.getFilho("conta"), conta.getFilho("saldo"));
    }
}
