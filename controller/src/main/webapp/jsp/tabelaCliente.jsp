<%@ page import="java.util.*" %>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante" %>
<%@ page import="com.indracompany.treinamento.model.service.ClienteService" %>
<%@ page import="com.indracompany.treinamento.model.entity.Cliente" %>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento" %>

<%@ page contentType="text/html; charset=ISO-8859-5" %>
<html>
<head>
    <title>Clientes</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body bgcolor="white">
    <h1>Lista de Clientes</h1>
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
			Elemento elementoListaCiente  = service.listarNomeCliente();
			
			for(Elemento elementoCliente: elementoListaCiente.getFilhos("cliente")) {
			%>
                <tr>
                    <!-- Nome do Cliente -->
                    <td><%= elementoCliente.getValor("nome") %></td>
                    <!-- Adicione mais colunas se houver outros dados, como id, email, etc. -->
                    <td>Agencia</td>
                    <td>Conta</td>
                    <td>Saldo</td>
                </tr>
            <% 
                } 
            %>
        </tbody>
    </table>
</body>
</html>

</html>
