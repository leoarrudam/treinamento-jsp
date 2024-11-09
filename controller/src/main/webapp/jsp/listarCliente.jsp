<%--
 
Projeto: TreinamentoJSP

Exibir um combox com os clientes utilizando a classe Elemento
  
--%>

<%@ page import="java.util.*"%>
<%@ page
	import="com.indracompany.treinamento.util.service.ServiceInstante"%>
<%@ page import="com.indracompany.treinamento.model.service.ContaBancariaService" %>
<%@ page import="com.indracompany.treinamento.model.entity.ContaBancaria"%>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento"%>

<%@ page contentType="text/html; charset=ISO-8859-5"%>
<html>
<head>
    <title>Correntistas</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body bgcolor="white">
    <%-- <h2>Clientes</h2>
    <table>
        <thead>
            <tr>
                <th>Nome</th>
                <th>Agência</th>
                <th>Conta</th>
                <th>Saldo</th>
            </tr>
        </thead>
        <tbody>
            <% 

                ServiceInstante<ClienteService> serviceInstance = new ServiceInstante<ClienteService>();
                ClienteService service = serviceInstance.get("clienteService", request);
                
                Elemento listaCorrentistas = service.listarCorrentistas();
               	System.out.println(listaCliente)
                
                // Iterando sobre cada cliente e gerando as linhas da tabela
                for(Elemento elementoCliente: listaCorrentistas.getFilhos("cliente")) {
                    String nome = elementoCliente.getValor("nome");
                    String agencia = elementoCliente.getValor("agencia");
                    String conta = elementoCliente.getValor("conta");
                    String saldo = elementoCliente.getValor("saldo");
            %>
            <tr>
                <td><%= nome %></td>
                <td><%= agencia %></td>
                <td><%= conta %></td>
                <td><%= saldo %></td>
            </tr>
            <% } %>
        </tbody>
    </table> --%>
    

	<b>Correntista:</b>
	<%-- <select name=cmbCliente>
		<% 

			ServiceInstante<ContaBancariaService> serviceInstance = new ServiceInstante<ContaBancariaService>();
			
			ContaBancariaService service = serviceInstance.get("contaBancariaService", request);
			Elemento elementoListaCorrentistas  = service.listarCorrentistas();
			
			for(Elemento elementoListaCorrentistas: elementoListaCorrentistas.getFilhos("ContaBancaria")) {
			%>
			
					<option><%= elementoListaCorrentistas.getValor("agencia") %></option>
			
					<%
			  }
			%>
	</select> --%>
</body>
</html>
