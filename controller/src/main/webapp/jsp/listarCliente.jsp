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

<%@ page contentType="text/html; charset=ISO-8859-5"%>
<html>
<head>
<title>Cliente</title>
</head>
<body bgcolor="white">
	<%--
	<b>Cliente:</b>
	 
	 <select name=cmbCliente>
		<% 

	//		ServiceInstante<ClienteService> serviceInstance = new ServiceInstante<ClienteService>();
			
	//		ClienteService service = serviceInstance.get("clienteService", request);
	//		Elemento elementoListaCiente  = service.listarNomeCliente();
			
	//		for(Elemento elementoCliente: elementoListaCiente.getFilhos("cliente")) {
			%>
			
	//				<option><%= elementoCliente.getValor("nome") %></option>
			
					<%
			  }
			%>
			
	</select>
	--%>
	<b>Clientes:</b>
	
	<table>
	<thead>
    <tr>
      <th scope="col">Cliente</th>
      <th scope="col">Agencia</th>
      <th scope="col">Conta</th>
      <th scope="col">Saldo</th>
    </tr>
  </thead>
  <tbody>
  <% 
  ServiceInstante<ClienteService> serviceInstance = new ServiceInstante<ClienteService>();
	
	ClienteService service = serviceInstance.get("clienteService", request);
	Elemento elementoListaCiente  = service.listarClientesEContas();
	
	for(Elemento elementoCliente: elementoListaCiente.getFilhos("cliente")) {    
            %>
    <tr>
      <th scope="row"><%= elementoCliente.getValor("nome") %></th>
      <td><%= elementoCliente.getValor("agencia") %></td>
      <td><%= elementoCliente.getValor("conta") %></td>
      <td><%= elementoCliente.getValor("saldo") %></td>
      <%
			  }
			%>
    </tr>
    </tbody>
	</table>
</body>
</html>
