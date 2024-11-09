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
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fb;
            margin: 0;
            padding: 20px;
            height: 100vh;
        }

        h2 {
            color: #2c3e50;
            text-align: center;
            font-size: 24px;
            margin-bottom: 20px;
        }

        /* Estilos da tabela */
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        /* Cabeçalho da tabela */
        table th {
            background-color: #2980b9;
            color: #fff;
            padding: 12px;
            text-align: left;
            font-size: 14px;
            text-transform: uppercase;
        }

        /* Estilo das células */
        table td {
            padding: 12px;
            text-align: left;
            color: #555;
            font-size: 14px;
            border-bottom: 1px solid #ddd;
        }

        /* Linhas alternadas */
        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        table tr:hover {
            background-color: #ecf0f1;
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

        Elemento elementoListaContas = service.listarDetalhesDasContas();

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