<%--
 
Projeto: TreinamentoJSP

Exibir um combox com os clientes utilizando a classe Elemento
  
--%>

<%@ page import="java.util.*" %>
<%@ page
        import="com.indracompany.treinamento.util.service.ServiceInstante" %>
<%@ page
        import="com.indracompany.treinamento.model.service.ClienteService" %>
<%@ page import="com.indracompany.treinamento.model.entity.Cliente" %>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento" %>
<%@ page import="com.indracompany.treinamento.model.service.ContaBancariaService" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cliente</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body bgcolor="white">
<form method="post">
    <b>Cliente:</b>
    <select name="cmbCliente">
        <%
            ServiceInstante<ClienteService> serviceInstance = new ServiceInstante<>();
            ClienteService service = serviceInstance.get("clienteService", request);
            Elemento elementoListaCiente = service.listarNomeCliente();

            for (Elemento elementoCliente : elementoListaCiente.getFilhos("cliente")) {
        %>
        <option value="<%= elementoCliente.getValor("nome") %>"><%= elementoCliente.getValor("nome") %>
        </option>
        <%
            }
        %>
    </select>
    <input type="submit" value="Buscar">
</form>

<%
    String selectedCliente = request.getParameter("cmbCliente");
    if (selectedCliente != null && !selectedCliente.isEmpty()) {
        ContaBancariaService contaService = new ServiceInstante<ContaBancariaService>().get("contaBancariaService", request);
        Elemento elementoContaBancaria = contaService.buscaContaPorNome(selectedCliente);

        if (elementoContaBancaria != null) {
%>
<table border="1">
    <thead>
    <tr>
        <th>Nome do cliente</th>
        <th>Agência</th>
        <th>Número</th>
        <th>Saldo</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><%= elementoContaBancaria.getValor("nome") %>
        </td>
        <td><%= elementoContaBancaria.getValor("agencia") %>
        </td>
        <td><%= elementoContaBancaria.getValor("numero") %>
        </td>
        <td><%= elementoContaBancaria.getValor("saldo") %>
        </td>
    </tr>
    </tbody>
</table>
<%
        }
        else {
%>
<p>Conta não encontrada para este cliente</p>
<%
        }
    }
%>
</body>
</html>
