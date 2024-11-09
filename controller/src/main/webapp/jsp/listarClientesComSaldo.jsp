<%@ page import="java.util.*"%>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante"%>
<%@ page import="com.indracompany.treinamento.model.service.ClienteService"%>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento"%>

<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>Clientes e Saldos</title>
    <style>
        /* Estilo para a tabela */
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: yellow; /* Cor de fundo amarela */
        }

        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f0e68c; /* Cor de fundo mais suave para o cabeçalho */
        }
    </style>
</head>
<body bgcolor="white">
    <h2>Lista de Correntistas e Saldos:</h2>
    
    <!-- Tabela para exibir os clientes -->
    <table>
        <thead>
            <tr>
                <th>Cliente</th>
                <th>Agência</th>
                <th>Conta</th>
                <th>Saldo</th>
            </tr>
        </thead>
        <tbody>
            <%
                // Criando uma instância do serviço de clientes
                ServiceInstante<ClienteService> serviceInstance = new ServiceInstante<ClienteService>();
                ClienteService clienteService = serviceInstance.get("clienteService", request);

                // Obtendo a lista de clientes com saldo
                Elemento elementoListaClientes = clienteService.listarClientesComSaldo();

                // Iterando sobre os clientes e exibindo as informações na tabela
                for (Elemento elementoCliente : elementoListaClientes.getFilhos("cliente")) {
                    String nome = elementoCliente.getValor("nome");
                    String agencia = elementoCliente.getValor("agencia");
                    String numeroConta = elementoCliente.getValor("numero");
                    String saldo = elementoCliente.getValor("saldo");

                    // Convertendo valores para tipos adequados
                    String agenciaStr = agencia != null ? agencia : "0";
                    String numeroContaStr = numeroConta != null ? numeroConta : "0";

                    // Tentando parse do saldo como double
                    double saldoDouble = 0.0;
                    try {
                        saldoDouble = Double.parseDouble(saldo);
                    } catch (NumberFormatException e) {
                        saldoDouble = 0.0;
                    }
            %>
            <tr>
                <td><%= nome %></td>
                <td><%= agenciaStr %></td>
                <td><%= numeroContaStr %></td>
                <td>R$ <%= String.format("%.2f", saldoDouble) %></td> <!-- Exibe o saldo com 2 casas decimais -->
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
