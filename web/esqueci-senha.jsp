<%-- 
    Document   : esqueci-senha
    Created on : 04/09/2021, 00:26:43
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
        <form action ="EnviarNovaSenha" method="POST">
            <table>
                <h1>Digite o usuário para a recuperação da senha:</h1><input type="text" name="busUs">
                <h1>Digite o email para o envio da senha:</h1><input type="text" name="emailenv">
            </table>
            <button type="submit" id ="btEnvSenha" name="nome">Enviar</button>
        </form>
    </body>
</html>
