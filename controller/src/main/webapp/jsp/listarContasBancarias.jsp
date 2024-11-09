<%@ page import="com.indracompany.treinamento.model.service.ContaBancariaService" %>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante" %>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento" %><%--
  Created by IntelliJ IDEA.
  User: terce
  Date: 09/11/2024
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contas Correntes</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<table border="1">
    <thead>
        <tr>
            <th>Nome do cliente</th>
            <th>Agência</th>
            <th>Número</th>
            <th>Saldo</th>
        </tr>
    </thead>
    <tbody>
        <%
            ServiceInstante<ContaBancariaService> serviceInstance = new ServiceInstante<>();
            ContaBancariaService service = serviceInstance.get("contaBancariaService", request);
            Elemento elementoListaContas = service.listarContasBancarias();

            for (Elemento elementoContaBancaria : elementoListaContas.getFilhos("conta")) {
        %>
        <tr>
            <td><%= elementoContaBancaria.getValor("nome") %></td>
            <td><%= elementoContaBancaria.getValor("agencia") %></td>
            <td><%= elementoContaBancaria.getValor("numero") %></td>
            <td><%= elementoContaBancaria.getValor("saldo") %></td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>
</body>
</html>
