<%-- 
    Document   : cadastro-usuario
    Created on : 04/09/2021, 02:32:40
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Usuários</title>
    </head>
    <body>
        <h1>Cadastro de Usuário</h1>
        <div id = "label-aviso">Todos os campos marcados com um * são obrigatórios</div>
        <form action="CadastroUsuario" method="POST">
            <table>
                <tr><th>Nome*: </th><th><input type="text" name="nomeusu"></th></tr>
                <tr><th>Senha*: </th><th><input type="password" name="senhausu"></th></tr>
                <tr><th>Repita a Senha*: </th><th><input type="password" name="repsenhausu"></th></tr>
                <tr><th>Email*: </th><th><input type="text" name="emailusu"></th></tr>
                <tr><th>Lembrete p rec de senha*: </th><th><input type="text" name="perguntausu"></th></tr>
                <tr><th>Resposta do lembrete*: </th><th><input type="text" name="respostausu"></th></tr>
                <tr><th>Nível de acesso*: </th>
                    <th>
                        <select name="nivelusu" id="nivelusu">
                            <option value="0" selected="selected">Cliente</option>
                            <option value="1">Funcionario</option>
                            <option value="2">Gerente</option>
                        </select>
                    </th>
                </tr>
                
            </table>
            <button type="submit" id ="btcadasusu" class="log" name="btcdusu">Cadastrar</button>
        </form>
    </body>
</html>
