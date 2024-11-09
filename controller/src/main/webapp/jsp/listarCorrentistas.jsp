<%@ page import="java.util.*" %>
<%@ page import="com.indracompany.treinamento.util.service.ServiceInstante" %>
<%@ page import="com.indracompany.treinamento.model.service.ClienteService" %>
<%@ page import="com.indracompany.treinamento.util.xml.Elemento" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="pt_BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Lista de Correntistas</title>
  <style>
    * {
      font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    }

    table {
      width: 80%;
      border-collapse: collapse;
    }

    th,
    td {
      padding: 12px;
      text-align: left;
    }

    th {
      background-color: #ffea80;
      color: white;
      font-weight: bold;
      text-shadow: gray 1px 1px;
    }

    tr:nth-child(even) {
      background-color: #fff7d7;
    }

    tr:nth-child(odd) {
      background-color: #fffbec;
    }

    th,
    td {
      border: 1px solid #ddd;
    }
  </style>
</head>

<body bgcolor="white">
  <h1>Lista de Correntistas</h1>
  <table border="1">
    <tr>
      <th>Nome</th>
      <th>Agência</th>
      <th>Conta</th>
      <th>Saldo</th>
    </tr>
    <% ServiceInstante<ClienteService> serviceInstance = new ServiceInstante<ClienteService>();
        ClienteService service = serviceInstance.get("clienteService", request);
        Elemento elementoClientes = service.listarClientesComSaldo();

        for (Elemento elementoCliente : elementoClientes.getFilhos("cliente")) {
        %>
        <tr>
          <td>
            <%= elementoCliente.getValor("nome") %>
          </td>
          <td>
            <%= elementoCliente.getValor("agencia") %>
          </td>
          <td>
            <%= elementoCliente.getValor("conta") %>
          </td>
          <td>
            <%= elementoCliente.getValor("saldo") %>
          </td>
        </tr>
        <% } %>
  </table>
</body>

</html>