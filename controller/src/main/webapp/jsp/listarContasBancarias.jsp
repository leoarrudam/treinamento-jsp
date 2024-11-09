<%@ page import="com.indracompany.treinamento.model.service.ContaBancariaService" %>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante" %>
<%@ page import="com.indracompany.treinamento.model.entity.ContaBancaria" %>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento" %><%--
  Created by IntelliJ IDEA.
  User: mclar
  Date: 09/11/2024
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contas Bancarias</title>

    <style>
        body {
            font-family: "Courier New", Courier, monospace;
        }

        .table{
            width: 70%;
            margin: 5% auto;
            text-align: center;
            border-collapse: collapse;
            border: 1px solid #87A878;
        }

        .tr-header{
            background-color: #87A878;
            text-transform: uppercase;
            color: white;
            height: 3rem;
        }

        .tr-body{
            height: 2.5rem;
        }

        .tr-body:nth-child(even){
            background-color: #b0bc98;
        }
    </style>
</head>
<body>
    <table class="table">
        <thead>
            <tr class="tr-header">
                <th>Nome</th>
                <th>Agência</th>
                <th>Número</th>
                <th>Saldo</th>
            </tr>
        </thead>
        <tbody>
        <%

            ServiceInstante<ContaBancariaService> serviceInstance = new ServiceInstante<ContaBancariaService>();

            ContaBancariaService service = serviceInstance.get("contaBancariaService", request);
            Elemento elementoListaContaBancaria  = service.listarContasBancarias();

            for(Elemento elementoConta: elementoListaContaBancaria.getFilhos("contaBancaria")) {
        %>
        <tr class="tr-body">
            <td><%= elementoConta.getValor("nome") %></td>
            <td><%= elementoConta.getValor("agencia") %></td>
            <td><%= elementoConta.getValor("conta") %></td>
            <td><%= elementoConta.getValor("saldo") %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</body>
</html>
