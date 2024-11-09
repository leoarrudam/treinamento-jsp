<%--
 
Projeto: TreinamentoJSP

Exibir uma tabela com as contas bancárias utilizando a classe Elemento
  
--%>

<%@ page import="java.util.*" %>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante" %>
<%@ page import="com.indracompany.treinamento.model.service.ContaBancariaService" %>
<%@ page import="com.indracompany.treinamento.model.entity.ContaBancaria"%>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento" %>

<%@ page contentType="text/html; charset=ISO-8859-5" %>
<html>
<head>
    <title>Contas Bancarias</title>
</head>
<body bgcolor="white">
    <h2><h2>Lista de Contas Bacarias</h2></h2>
    
    <table border="1">
        <tr>
            <th>Cliente</th>
            <th>Agencia</th>
            <th>Numero</th>
            <th>Saldo</th>
        </tr>
        
        <%
            // Instância do service para obter os dados
            ServiceInstante<ContaBancariaService> serviceInstance = new ServiceInstante<ContaBancariaService>();
            ContaBancariaService contaService = serviceInstance.get("contaBancariaService", request);
            
            // Obtém o elemento XML com as contas bancárias
            Elemento elementoListaContas = contaService.listarContasBancariasPorCliente();
            
            // Itera sobre os elementos de conta bancária
            for (Elemento elementoConta : elementoListaContas.getFilhos("conta")) {
        %>
                <tr>
                    <td><%= elementoConta.getValor("cliente") %></td>
                    <td><%= elementoConta.getValor("agencia") %></td>
                    <td><%= elementoConta.getValor("numero") %></td>
                    <td><%= elementoConta.getValor("saldo") %></td>
                </tr>
        <%
            }
        %>
    </table>
</body>
</html>
