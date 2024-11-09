<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante" %>
<%@ page import="com.indracompany.treinamento.model.service.ClienteService" %>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th{
        	color: #fff;
        	font-weight: bold;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #FFD966;
        }
    </style>
<title>Informações Bancarias Client</title>
</head>
<body>

 <h1>Informações Bancárias dos Clientes</h1>
    <table>
        <tr>
            <th>Nome</th>
            <th>Agência</th>
            <th>Conta</th>
            <th>Saldo</th>
        </tr>
        <%
            ServiceInstante<ClienteService> serviceInstance = new ServiceInstante<ClienteService>();
            ClienteService service = serviceInstance.get("clienteService", request);

            Elemento elementoListaInfoClient = service.listarClientInfoBancario();

            NumberFormat formatter = NumberFormat.getInstance(new Locale("pt", "BR"));
            formatter.setMinimumFractionDigits(2);
            formatter.setMaximumFractionDigits(2);
            
            for (Elemento elementoCliente : elementoListaInfoClient.getFilhos("cliente")) {
                String nome = elementoCliente.getValor("nome");
                String agencia = elementoCliente.getValor("agencia");
                String conta = elementoCliente.getValor("conta");
                
                double saldo = Double.parseDouble(elementoCliente.getValor("saldo"));
                String saldoFormatado = formatter.format(saldo);
                
                String contaFormat;
                if (conta.length() > 1) {
                	contaFormat = conta.substring(0, conta.length() - 1) + "-" + conta.substring(conta.length() - 1);
                } else {
                	contaFormat = conta; 
                }
        %>
        <tr>
            <td><%= nome %></td>
            <td><%= agencia %></td>
            <td><%= contaFormat %></td>
            <td><%= saldoFormatado %></td>
        </tr>
        <%
            }
        %>
    </table>

</body>
</html>