<%@ page import="java.util.*" %>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante" %>
<%@ page import="com.indracompany.treinamento.model.service.ContaBancariaService" %>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento" %>

<%@ page contentType="text/html; charset=ISO-8859-5" %>
<html>
<head>
    <title>Lista de Correntistas</title>
    <style>
        th, td {
            padding: 12px;
            text-align: left;
            background-color: #fff;
            border: 1px solid #ddd;
        }
    </style>
</head>
<body>
<h2>Lista de Correntistas</h2>
<table>
    <thead>
    <tr>
        <th>Nome</th>
        <th>Agencia</th>
        <th>Conta</th>
        <th>Saldo</th>
    </tr>
    </thead>
    <tbody>
    <%
        ServiceInstante<ContaBancariaService> serviceInstance = new ServiceInstante<ContaBancariaService>();
        ContaBancariaService service = serviceInstance.get("contaBancariaService", request);

        Elemento elementoListaContas = service.listarTodasContas();

        for (Elemento elementoConta : elementoListaContas.getFilhos("conta")) {
    %>
    <tr>
        <td><%= elementoConta.getValor("nome") %></td>
        <td><%= elementoConta.getValor("agencia") %></td>
        <td><%= elementoConta.getValor("numero") %></td>
        <td><%= elementoConta.getValor("saldo") %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
