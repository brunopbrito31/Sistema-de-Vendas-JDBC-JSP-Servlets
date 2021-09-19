<%-- 
    Document   : autenticacaocadasusu
    Created on : 04/09/2021, 03:46:17
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Criação de gerente, necessária autorização de um dos pares</h1>
        <form action="Autenticar" method="POST">
            Entre com Usuario:<input type="text" name="nome"><br>
            Entre com Senha  :<input type="text" name="senha"><br>
            <button type="submit" id ="btautoger" class="log" name="btcdau">Autorizar</button>
        </form>
    </body>
</html>
