<%--
  Created by IntelliJ IDEA.
  User: wesle
  Date: 09/11/2024
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.indracompany.treinamento.model.service.ClienteService"%>
<%@ page import="com.indracompany.treinamento.model.service.ContaBancariaService"%>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante"%>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Correntistas</title>
</head>
<body>
<div align="center">
    <h1>Clientes Correntistas</h1>
    <table border="solid 1px black" width="500px">
        <thead align="center">
        <tr>
            <td>Nome</td>
            <td>Agencia</td>
            <td>Conta</td>
            <td>Saldo</td>

        </tr>

        </thead>
        <tbody>
        <%
            ServiceInstante<ContaBancariaService> serviceInstance = new ServiceInstante<ContaBancariaService>();

            ContaBancariaService contaBancariaService = serviceInstance.get("contaBancariaService", request);

            Elemento elementoListaContaBancaria = contaBancariaService.listarContaBancaria();

            for (Elemento elementoContaBancaria: elementoListaContaBancaria.getFilhos("contaBancaria")) {
        %>
        <tr>
            <td><%= elementoContaBancaria.getValor("nome")  %></td>
            <td><%= elementoContaBancaria.getValor("agencia")  %></td>
            <td><%= elementoContaBancaria.getValor("numeroConta")  %></td>
            <td><%= elementoContaBancaria.getValor("saldo")  %></td>
        </tr>
        <%
            }
        %>

        </tbody>
    </table>
</div>


</body>
</html>
