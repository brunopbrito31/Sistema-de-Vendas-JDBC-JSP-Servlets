<%-- 
    Document   : enviararquivo
    Created on : 03/09/2021, 21:35:27
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de upload</title>
    </head>
    <body>
        <h1>Bem vindo a página de upload</h1><br>
        <form action = "Enviar" enctype="multipart/form-data" method="GET">
            Nome do Arquivo: <input type ="text" name="arqenv"><br>
            <input type ="file" name="upload"><button type="submit">Enviar</button> 
        </form> 
    </body>
</html>
