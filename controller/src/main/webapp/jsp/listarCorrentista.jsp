<%--
 
Projeto: TreinamentoJSP

Exibir uma tabela com os correntistas utilizando a classe Elemento
  
--%>

<%@ page import="java.util.*"%>
<%@ page
	import="com.indracompany.treinamento.util.service.ServiceInstante"%>
<%@ page
	import="com.indracompany.treinamento.model.service.ContaBancariaService"%>
<%@ page import="com.indracompany.treinamento.model.entity.ContaBancaria"%>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento"%>

<%@ page contentType="text/html; charset=ISO-8859-5"%>
<html>
<head>
<title>Correntistas</title>
<style>
	body {
		background-color: #f0f8ff;
    }
	table {
		width: 100%;
		border-collapse: collapse;
		font-family: Arial, sans-serif;
	}
	
	th {
		background-color: #f2f2f2;
		border: 1px solid #ddd;
		padding: 8px;
		text-align: left;
	}

	td {
		border: 1px solid #ddd;
		padding: 8px;
		background-color: #fafafa;
	}

	tr:nth-child(even) {
		background-color: #f5f5f5;
	}
</style>
</head>
<body bgcolor="white">
	<h2>Correntistas</h2>
	<table>
		<tr>
			<th>Nome</th>
			<th>Agencia</th>
			<th>Numero</th>
			<th>Saldo</th>
    	</tr>
	<% 
		ServiceInstante<ContaBancariaService> serviceInstance = new ServiceInstante<ContaBancariaService>();
		ContaBancariaService service = serviceInstance.get("contaBancariaService", request);
		Elemento elementoListaContaBancaria = service.listarContaBancaria();

		for(Elemento elementoContaBancaria: elementoListaContaBancaria.getFilhos("contaBancaria")) {
			%>
				<tr>
					<td><%= elementoContaBancaria.getValor("nome")%></td>
					<td><%= elementoContaBancaria.getValor("agencia")%></td>
					<td><%= elementoContaBancaria.getValor("numero")%></td>
					<td><%= elementoContaBancaria.getValor("saldo")%></td>
				</tr>
				
			<%    
		}
		%>
	</table>
</body>
</html>
