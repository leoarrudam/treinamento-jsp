<%--
 
Projeto: TreinamentoJSP

Exibir um combox com os clientes utilizando a classe Elemento
  
--%>

<%@ page import="java.util.*"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page
	import="com.indracompany.treinamento.util.service.ServiceInstante"%>
<%@ page import="com.indracompany.treinamento.model.service.ContaBancariaService" %>
<%@ page import="com.indracompany.treinamento.model.entity.ContaBancaria"%>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento"%>

<%@ page contentType="text/html; charset=ISO-8859-5"%>
<html>
<head>
    <title>Correntistas</title>
    <style>
         body {
            font-family: 'Roboto', sans-serif;
            background-color: #f9f9f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #2C3E50;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            margin-bottom: 20px;
        }

        th, td {
            padding: 12px 20px;
            text-align: left;
            border: 1px solid #ddd;
            font-size: 16px;
        }

        th {
            background-color: #2980B9;
            color: white;
            font-weight: 500;
        }

        td {
            background-color: #fafafa;
        }

        tr:nth-child(even) td {
            background-color: #f2f2f2;
        }

        tr:hover td {
            background-color: #f5a623;
            color: #fff;
        }
    </style>
</head>
<body bgcolor="white">
    <h2>Nossos correntistas</h2>
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
                
                Elemento listaCorrentistas = service.listarCorrentistas();
                

                for(Elemento elementoCliente: listaCorrentistas.getFilhos("cliente")) {
                    String nome = elementoCliente.getValor("nome");
                    String agencia = elementoCliente.getValor("agencia");
                    String conta = elementoCliente.getValor("conta");
                    String saldo = elementoCliente.getValor("saldo");
                    
            %>
            <tr>
                <td><%= nome %></td>
                <td><%= agencia %></td>
                <td><%= conta %></td>
                <td>R$ <%= saldo %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
