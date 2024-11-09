<%--
 
Projeto: TreinamentoJSP

Exibir um combox com os clientes utilizando a classe Elemento
  
--%>

<%@ page import="java.util.*"%>
<%@ page
	import="com.indracompany.treinamento.util.service.ServiceInstante"%>
<%@ page
	import="com.indracompany.treinamento.model.service.ClienteService"%>
<%@ page import="com.indracompany.treinamento.model.entity.Cliente"%>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento"%>
<%@ page import="com.indracompany.treinamento.model.service.ContaBancariaService" %>

<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Cliente</title>
</head>
<body bgcolor="white">
<h2>Lista de Correntistas:</h2>
<table border="1">
	<tr>
		<th>Nome</th>
		<th>Agência</th>
		<th>Conta</th>
		<th>Saldo</th>
	</tr>
	<%

		ServiceInstante<ContaBancariaService> serviceInstance = new ServiceInstante<ContaBancariaService>();
		ContaBancariaService contaBancariaService = serviceInstance.get("contaBancariaService", request);


		Elemento listaContas = contaBancariaService.listarTodasContas();


		for (Elemento elementoConta : listaContas.getFilhos("conta")) {
	%>
	<tr>
		<td><%= elementoConta.getValor("clienteNome") %></td>
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
