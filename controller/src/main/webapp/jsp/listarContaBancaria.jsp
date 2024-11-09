<%@ page import="com.indracompany.treinamento.model.service.ContaBancariaService" %>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante" %>
<%@ page import="com.indracompany.treinamento.util.ContaBancaria.ContaBancariaUtil" %>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 09/11/2024
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contas Bancárias</title>
</head>
<body style="font-family: sans-serif; margin: 0; padding: 0" >

<header style="background-color: darkblue; color: #ffffff; font-weight: bold; height: 30px; padding: 10px; display: flex; flex-direction: column; justify-content: center" >Banco do povo</header>

<h2 style="font-size: 1em; margin-left: 50px">Lista dos correntistas</h2>

<table border="1" style="margin: 20px 0 0 50px; " >
    <thead>
    <tr>
        <th>Nome</th>
        <th>Agencia</th>
        <th>Conta</th>
        <th>Saldo</th>
    </tr>
    </thead>
    <tbody>
        <%-- pegando todos os clientes do banco e iterando sob cada um --%>
        <%
            ServiceInstante<ContaBancariaService> serviceInstance = new ServiceInstante<ContaBancariaService>();

            ContaBancariaService service = serviceInstance.get("contaBancariaService", request);

            Elemento elementoListaContasBancaria = service.listarContasBancarias();

            for (Elemento contaBancaria: elementoListaContasBancaria.getFilhos("conta")) {
        %>

        <tr>
            <%= ContaBancariaUtil.imprimirTuplaHtml(contaBancaria) %>
        </tr>

        <%
            }
        %>
    </tbody>
</table>

</body>
</html>
