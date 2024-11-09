<%@ page import="com.indracompany.treinamento.model.service.ContaBancariaService" %>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante" %>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento" %><%--
  Created by IntelliJ IDEA.
  User: gabriella
  Date: 09/11/2024
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contas bancárias</title>
</head>
<body>
    <table style="width: 70%; margin: 5% auto; border-collapse: collapse; text-align: center">
        <thead style="background-color: #ff8fab">
        <tr style="height: 3rem; color: white; font-weight: 900">
            <th>Nome</th>
            <th>Agência</th>
            <th>Conta</th>
            <th>Saldo</th>
        </tr>
        </thead>


    <%
    ServiceInstante<ContaBancariaService> serviceInstance = new ServiceInstante<ContaBancariaService>();

        ContaBancariaService service = serviceInstance.get("contaBancariaService", request);
        Elemento elementoListaContaBancaria  = service.listarContasBancarias();

        for(Elemento elementoConta: elementoListaContaBancaria.getFilhos("contaBancaria")) {
        %>
        <tr style="background-color: #ffe5ec; height: 3rem">
            <td><%= elementoConta.getValor("nome") %></td>
            <td><%= elementoConta.getValor("agencia") %></td>
            <td><%= elementoConta.getValor("conta") %></td>
            <td><%= elementoConta.getValor("saldo") %></td>
        </tr>
            <%
            }
        %>



    </table>
</body>
</html>
