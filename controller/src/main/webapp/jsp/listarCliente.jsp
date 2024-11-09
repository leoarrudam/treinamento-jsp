<%@ page import="java.util.*"%>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante"%>
<%@ page import="com.indracompany.treinamento.model.service.ClienteService"%>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento"%>

<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>Cliente</title>
</head>
<body bgcolor="white">
    <b>Cliente:</b>
    <select name="cmbCliente">
        <%
            // Criando uma instância do serviço ClienteService
            ServiceInstante<ClienteService> serviceInstance = new ServiceInstante<ClienteService>();
            ClienteService service = serviceInstance.get("clienteService", request);

            // Obtendo os clientes
            Elemento elementoListaCliente = service.listarClientesComSaldo(); // Mudança: alterei para o método correto (ou ajuste conforme necessário)

            // Iterando sobre os filhos "cliente" dentro do elemento
            for (Elemento elementoCliente : elementoListaCliente.getFilhos("cliente")) {
        %>
            <!-- Exibindo o nome do cliente dentro da opção, incluindo o 'value' -->
            <option value="<%= elementoCliente.getValor("nome") %>">
                <%= elementoCliente.getValor("nome") %>
            </option>
        <%
            }
        %>
    </select>
</body>
</html>
