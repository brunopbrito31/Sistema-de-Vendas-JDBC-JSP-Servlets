<%-- 
    Document   : reclamacao.jsp
    Created on : 02/09/2021, 21:15:27
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Reclamação</title>
    </head>
    <body>
        <form action="Reclamar" method="POST">
            <div>
                <table>
                    <tr><th><h1>Nome: </h1></th><th><input type="text" name="assunto"></th></tr>
                    <tr><th><h1>Email: </h1></th><th><input type="text" name="email"></th></tr>
                    <tr><th><h1>Reclamação: </h1></th><th><input type="text" name="reclamacao"></th></tr>
                </table>
            </div>
            <button type="submit" name="btcadastrar_cli">Cadastrar</button>
        </form>
    </body>
</html>
