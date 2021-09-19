<%-- 
    Document   : pag-estoque
    Created on : 14/09/2021, 22:50:30
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estoque</title>
        <link rel="stylesheet" type="text/css" href="estoque/assets/estoqueinicial.css">
    </head>
    <body>
        <form action="Estoque" method="GET">
            <select name="tip-bus">
                <option value="codBarras">Cod.Barras:</option>
                <option value="nome" selected="true">Nome:</option>
                <option value="categoria">Categoria:</option>
            </select>
            <input type="text" name="cod">
            <button type="submit">Pesquisar</button>
        </form>
        <h1>Pagina de Estoque</h1><br> 
        ${lista} 
    </body>
</html>
