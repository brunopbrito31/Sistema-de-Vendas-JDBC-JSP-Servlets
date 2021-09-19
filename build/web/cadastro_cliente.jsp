<%-- 
    Document   : cadastro_cliente
    Created on : 31/08/2021, 21:35:51
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de clientes</title>
        <link rel="stylesheet" type="text/css" href="assets/cadastro_cliente.css">
    </head>
    <body>
        <h1>Cadastro de cliente</h1><br>
        <div>
        <form action="CadastrarCliente" method="POST">
            <div>
                <table>
                    <tr><th><h1>Nome: </h1></th><th><input type="text" name="nome"></th></tr>
                    <tr><th><h1>Idade: </h1></th><th><input type="text" name="idade"></th></tr>
                    <tr><th><h1>Peso: </h1></th><th><input type="text" name="peso"></th></tr>
                </table>
            </div>
            <button type="submit" name="btcadastrar_cli">Cadastrar</button>
        </form>
        </div>
    </body>
</html>
