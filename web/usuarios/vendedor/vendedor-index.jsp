<%-- 
    Document   : vendedor-index
    Created on : 07/09/2021, 05:10:42
    Author     : bruno
--%>

<%@page import="tools.Auxiliar"%>
<%@page import="java.time.Instant"%>
<%@page import="java.util.Date"%>
<%@page import="models.entities.dao.FabricaDAO"%>
<%@page import="models.entities.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Operação de Vendas</title>
        <link rel="stylesheet" type="text/css" href="assets/vendedor.css">
    </head>
    <body>
        <header id = 'top-menu'>
            <% out.print("<div>"+Auxiliar.retornarDataPadrao   (Date.from(Instant.now()))+"</div><br>"); %>
            <h1>Olá <% out.print(request.getParameter("username")); %>, seja bem vindo!</h1><br>
        </header><br>
        <div id="botoesdovendedor">
            <form action="ListarProdutos" method="POST">
                <button type="submit" id ="btlistar1" name="produtopesquisado" class="btvendedor">Catalogo de Produtos</button>
            </form><br>

            <form action="vendas/realizar-vendas.jsp" method="POST">
                <button type="submit" id ="btlistar2" name="realizarvenda" class="btvendedor">Realizar Venda</button>
            </form><br>

            <form action="produtos/listar-produtos.jsp" method="POST">
                <button type="submit" id ="btlistar3" name="consultarproduto" class="btvendedor">Consultar Produto</button>
            </form><br>
            
            <form action="ProdCadastro" method="POST">
                <button type="submit" class="btvendedor">Cadastro de Produto</button>
            </form>

        </div>
    </body>
</html>
