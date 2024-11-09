<%@ page import="java.util.*"%>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante"%>
<%@ page import="com.indracompany.treinamento.model.service.ClienteService"%>
<%@ page import="com.indracompany.treinamento.model.entity.Cliente"%>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento"%>

<%@ page contentType="text/html; charset=ISO-8859-5"%>
<html>
<head>
    <title>Clientes</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body bgcolor="white">
    <b>Clientes:</b>
    
    <!-- Tabela para exibir os clientes -->
    <table>
        <thead>
            <tr>
                <th>Nome</th>
                <th>Número da Conta</th>
                <th>Agência</th>
                <th>Saldo</th>
            </tr>
        </thead>
        <tbody>
            <%
                // Instância do serviço de clientes
                ServiceInstante<ClienteService> serviceInstance = new ServiceInstante<ClienteService>();
                ClienteService service = serviceInstance.get("clienteService", request);
                
                // Lista de clientes
                Elemento elementoListaCliente = service.listarNomeCliente();

                // Iterando sobre a lista de clientes para preencher a tabela
                for (Elemento elementoCliente : elementoListaCliente.getFilhos("cliente")) {
                    String nome = elementoCliente.getValor("nome");
                    String numeroConta = elementoCliente.getValor("numeroConta");
                    String agencia = elementoCliente.getValor("agencia");
                    String saldo = elementoCliente.getValor("saldo");
            %>
                <tr>
                    <td><%= nome %></td>
                    <td><%= numeroConta %></td>
                    <td><%= agencia %></td>
                    <td><%= saldo %></td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
