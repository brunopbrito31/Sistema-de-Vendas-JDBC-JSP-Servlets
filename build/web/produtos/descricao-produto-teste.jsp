<%-- 
    Document   : descricao-produto-teste
    Created on : 07/09/2021, 19:09:18
    Author     : bruno
--%>

<%@page import="tools.Auxiliar"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="models.entities.Produto"%>
<%@page import="models.entities.dao.ProdutoDAO"%>
<%@page import="models.entities.dao.FabricaDAO"%>
<% ProdutoDAO produtoDAO = FabricaDAO.criarProdutoDAO(); %>
<% 
    String filtroPesquisa = request.getParameter("produtopesquisado");
%>
<% List<Produto> produtosCatalogo = produtoDAO.buscarTodosOsProdutosCFiltro(filtroPesquisa,"",""); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
