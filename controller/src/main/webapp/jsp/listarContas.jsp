<%--
 Projeto: TreinamentoJSP
 Exibir uma tabela com as contas bancárias utilizando a classe Elemento
--%>

<%@ page import="java.util.*"%>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante"%>
<%@ page import="com.indracompany.treinamento.model.service.ContaBancariaService"%>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento"%>

<%@ page contentType="text/html; charset=ISO-8859-5"%>
<html>
<head>
  <title>Lista de Contas Bancarias</title>
</head>
<body bgcolor="white">
<h2>Contas Bancarias</h2>

<table border="1">
  <thead>
  <tr>
    <th>Nome do Cliente</th>
    <th>Agencia</th>
    <th>Numero da Conta</th>
    <th>Saldo</th>
  </tr>
  </thead>
  <tbody>
  <%
    // Instancia o serviço ContaBancariaService
    ServiceInstante<ContaBancariaService> serviceInstance = new ServiceInstante<ContaBancariaService>();
    ContaBancariaService service = serviceInstance.get("contaBancariaService", request);

    // Chama o método que retorna a lista de contas com detalhes
    Elemento elementoListaContas = service.listarContasComDetalhes();

    // Itera sobre os elementos de conta
    for(Elemento elementoConta : elementoListaContas.getFilhos("conta")) {
  %>
  <tr>
    <td><%= elementoConta.getValor("nome") %></td>
    <td><%= elementoConta.getValor("agencia") %></td>
    <td><%= elementoConta.getValor("numero") %></td>
    <td><%= elementoConta.getValor("saldo") %></td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>
</body>
</html>
