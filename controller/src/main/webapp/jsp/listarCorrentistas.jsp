<%--
 
Projeto: TreinamentoJSP

Exibir um combox com os clientes utilizando a classe Elemento
  
--%>

<%@ page import="java.util.*"%>
<%@ page
	import="com.indracompany.treinamento.util.service.ServiceInstante"%>
<%@ page
	import="com.indracompany.treinamento.model.service.ContaBancariaService"%>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento"%>

<%@ page contentType="text/html; charset=ISO-8859-5"%>
<html>
<head>
<title>Correntistas</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f9;
	color: #333;
	padding: 20px;
}

h2 {
	color: #4a4a7b;
	text-align:center;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table, th, td {
	border: 1px solid #ddd;
}

th, td {
	padding: 12px;
	text-align: left;
}

th {
	background-color: #4a4a7b;
	color: white;
	font-weight: bold;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

tr:hover {
	background-color: #e0e0e0;
}
</style>
</head>
<body bgcolor="white">
	<h2>Correntistas:</h2>
	<Table border="1">
		<tr>
			<th>Nome</th>
			<th>Agencia</th>
			<th>Conta</th>
			<th>Saldo</th>
		</tr>
		<%
		ServiceInstante<ContaBancariaService> serviceInstance = new ServiceInstante<ContaBancariaService>();

		ContaBancariaService service = serviceInstance.get("contaBancariaService", request);

		Elemento elementoListaCorrentistas = service.ListarCorrentistas();

		for (Elemento elementoCorrentista : elementoListaCorrentistas.getFilhos("contaUsuario")) {
		%>

		<tr>
			<th><%=elementoCorrentista.getValor("nome")%></th>
			<th><%=elementoCorrentista.getValor("agencia")%></th>
			<th><%=elementoCorrentista.getValor("conta")%></th>
			<th><%=elementoCorrentista.getValor("saldo")%></th>
		</tr>

		<%
		}
		%>
	</Table>
</body>
</html>
