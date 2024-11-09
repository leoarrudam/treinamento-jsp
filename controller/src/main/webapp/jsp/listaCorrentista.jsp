

<%@ page import="java.util.*"%>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante" %>
<%@ page import="com.indracompany.treinamento.model.service.ContaBancariaService" %>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento" %>

<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<title>Lista de Correntistas e seus Saldos</title>
</head>
<body bgcolor="#020821" style="font-family: Arial, sans-serif; margin: 30px; color:white ">
	<h2 style="color: white; text-align: center; font-size: 24px;">Lista de Correntistas e seus Saldos</h2>
	<table border="1" style="width: 100%; border-collapse: collapse; margin-top: 20px;">
		<tr style="background-color: #cfc506; color:#edecda;">
			<th style="padding: 10px; border: 1px solid #cfc506; font-weight: bold;">Nome</th>
			<th style="padding: 10px; border: 1px solid #cfc506; font-weight: bold;">Agência</th>
			<th style="padding: 10px; border: 1px solid #cfc506; font-weight: bold;">Conta</th>
			<th style="padding: 10px; border: 1px solid #cfc506; font-weight: bold;">Saldo</th>
		</tr>
		<%
			
			ServiceInstante<ContaBancariaService> serviceInstance = new ServiceInstante<ContaBancariaService>();
			ContaBancariaService service = serviceInstance.get("contaBancariaService", request);
			
	
			Elemento elementoListaCorrentistas = service.listarCorrentistasComSaldo();
			
			for (Elemento elementoCorrentista : elementoListaCorrentistas.getFilhos("correntista")) {
		%>
			<tr style="text-align: center; background-color:#b8b474;">
				<td style="padding: 10px; border: 1px solid #b8b474; color:black;"><%= elementoCorrentista.getValor("nome") %></td>
				<td style="padding: 10px; border: 1px solid #b8b474; color:black;"><%= elementoCorrentista.getValor("agencia") %></td>
				<td style="padding: 10px; border: 1px solid #b8b474; color:black;"><%= elementoCorrentista.getValor("conta") %></td>
				<td style="padding: 10px; border: 1px solid #b8b474; color: green; font-weight: bold;"><%= elementoCorrentista.getValor("saldo") %></td>
			</tr>
		<%
			}
		%>
	</table>
</body>
</html>

