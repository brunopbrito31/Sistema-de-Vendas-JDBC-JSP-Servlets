<%-- 
    Document   : estoquista-index
    Created on : 07/09/2021, 05:10:33
    Author     : bruno
--%>

<%@page import="java.time.Instant"%>
<%@page import="java.util.Date"%>
<%@page import="tools.Auxiliar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% request.setAttribute("prodNome","");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Operações Logisticas</title>
        <link rel="stylesheet" type="text/css" href="assets/estoquista.css">
    </head>
    <body>
        <% out.print("<div>"+Auxiliar.retornarDataPadrao   (Date.from(Instant.now()))+"</div><br>"); %>
        <h1>Olá <% out.print(request.getParameter("username")); %>, seja bem vindo!</h1>
        
        
        <ul>
            <li>
                <form action="ProdCadastro" method="POST">
                    <button type="submit" class="fun_est_op">Cadastro de Produto</button>
                </form>
            </li>
            <li>
                <form action="ProdListar" method="POST">
                    <button type="submit" class="fun_est_op">Produtos Cadastrados</button>
                </form>
            </li>
            <li>
                <form action="Estoque" method="POST">
                    <button type="submit" class="fun_est_op">Estoque</button>
                </form>
            </li>
            <li>Consultar Produto</li>
            <li>Desativar Produto</li>
            <li>Ativar Produto</li>
        </ul>
    </body>
</html>
