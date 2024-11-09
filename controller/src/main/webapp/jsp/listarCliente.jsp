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
<b>Cliente:</b>
<select name=cmbCliente>
    <%

        ServiceInstante<ClienteService> serviceInstance = new ServiceInstante<ClienteService>();

        ClienteService service = serviceInstance.get("clienteService", request);
        Elemento elementoListaCiente  = service.listarNomeCliente();

        for(Elemento elementoCliente: elementoListaCiente.getFilhos("cliente")) {
    %>

    <option><%= elementoCliente.getValor("nome") %></option>

    <%
        }
    %>
</select>
</body>
</html>
