<%@ page import="java.util.*" %>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante" %>
<%@ page import="com.indracompany.treinamento.model.service.ContaBancariaService" %>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento" %>

<%@ page contentType="text/html; charset=ISO-8859-5" %>
<html>
<head>
    <title>Lista de Correntistas</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0 10px;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            background-color: #fff;
            border: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f2f2f2;
        }
        td {
            font-size: 14px;
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
