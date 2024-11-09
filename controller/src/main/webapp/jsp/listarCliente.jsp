<%@ page import="java.util.*" %>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante" %>
<%@ page import="com.indracompany.treinamento.model.service.ClienteService" %>
<%@ page import="com.indracompany.treinamento.model.entity.Cliente" %>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento" %>

<%@ page contentType="text/html; charset=ISO-8859-5" %>
<html>
<head>
    <title>Lista de Correntistas</title>
</head>
<body bgcolor="white">
    <h2>Lista de Correntistas e Saldos</h2>
    <select name="cmbCliente">
        <%
            // Instância de serviço para buscar dados dos clientes
            ServiceInstante<ClienteService> serviceInstance = new ServiceInstante<>();
            ClienteService service = serviceInstance.get("clienteService", request);

            // Obter a lista de clientes como Elemento
            Elemento elementoListaCliente = service.listarDadosClientes();

            // Iterar sobre cada cliente e exibir seus dados
            for (Elemento elementoCliente : elementoListaCliente.getFilhos("cliente")) {
        %>
                <option>
                    <%= "Nome: " + elementoCliente.getValor("nome") %> |
                    <%= "Agência: " + elementoCliente.getValor("agencia") %> |
                    <%= "Conta: " + elementoCliente.getValor("conta") %> |
                    <%= "Saldo: " + elementoCliente.getValor("saldo") %>
                </option>
        <%
            }
        %>
    </select>
</body>
</html>