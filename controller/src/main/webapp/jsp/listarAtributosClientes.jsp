<%--
 
Projeto: TreinamentoJSP

Exibir um combox com os clientes utilizando a classe Elemento
  
--%>

<%@ page import="java.util.*"%>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante"%>
<%@ page import="com.indracompany.treinamento.model.service.ClienteService"%>
<%@ page import="com.indracompany.treinamento.model.entity.Cliente"%>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento"%>

<%@ page contentType="text/html; charset=ISO-8859-5"%>
<html>
<head>
<title>Lista de todos os correntistas do banco</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<h1>Lista de todos os correntistas do banco:</h1>
	<div id="tabela">
		<table>
		<thead>
			<tr>				
				<th>Nome</th>
				<th>Agencia</th>
				<th>Conta</th>
				<th>Saldo</th>
			</tr>
		</thead>
		<tbody>
			<%
			
			ServiceInstante<ClienteService> serviceInstance = new ServiceInstante<ClienteService>();
			
			ClienteService service = serviceInstance.get("clienteService", request);
			Elemento elementoListaCliente  = service.listarAtributosClientes();
			
			for (Elemento elementoCliente: elementoListaCliente.getFilhos("cliente")) {%>
				<tr>
					<%
					Elemento elementoListaContaBancaria = elementoCliente.getFilho("listaContaBancaria");
					
					if (elementoListaContaBancaria.contemFilho("contaBancaria")) {
						for (Elemento elementoContaBancaria: elementoListaContaBancaria.getFilhos("contaBancaria")) {
					%>
							<td><%= elementoCliente.getValor("nome") %></td>
							<td><%= elementoContaBancaria.getValor("agencia") %></td>
							<td><%= elementoContaBancaria.getValor("conta") %></td>
							<td><%= elementoContaBancaria.getValor("saldo") %></td>
					<%
						}
					} else {
					%>
						<td><%= elementoCliente.getValor("nome") %></td>
						<td colspan="3">Nao possui conta bancaria</td>
					<%} %>
				</tr>
		  <%} %>
		</tbody>
	</table>
	</div>
	
</body>
</html>
