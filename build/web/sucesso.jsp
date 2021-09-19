<%-- 
    Document   : sucesso.jsp
    Created on : 31/08/2021, 00:34:31
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tela Inicial</title>
        <link rel="stylesheet" type="text/css" href="assets/main.css">
    </head>
    
        
    <% 
        String nome = request.getParameter("nome");
        out.print("<div><h1>Olá "+nome+", Seja Bem Vindo!</h1><br></div>");
    %>
        
        
<!--        <div><h1>Olá ${nome} seja bem vindo!</h1><br></div>-->
        <div>
            <h3>Sistema de Vendas Basic Smart 1.0</h3><br>
            <div id="botoes-principal">
                <form action = "cadastro_cliente.jsp" method="POST">
                    <input type ="submit" name="btn_cad_cliente" id="btn_cad_cliente" value ="Cadastrar Cliente">  
                </form>
                <form action = "Vender" method="POST">               
                    <input type ="submit" name="btn_cad_venda" id="btn_cad_venda" value ="Realizar Venda">  
                </form>
                <form action = "Consultar" method="POST">               
                    <input type ="submit" name="btn_cons_cliente" id="btn_cons_clientes" value ="Consultar Clientes">  
                </form>
                <form action = "ConsultarV" method="POST">               
                    <input type ="submit" name="btn_cons_venda" id="btn_cons_vendas" value ="Consultar Vendas">  
                </form>
                <form action = "Modificar" method="POST">               
                    <input type ="submit" name="btn_atualizar_cliente" id="btn_mod_cliente" value ="Modificar Clientes">  
                </form>
                <form action = "reclamacao.jsp" method="POST">               
                    <input type ="submit" name="btn_reclamar" id="btn_reclamacao" value ="Reclamar">  
                </form>
                <form action = "enviararquivo.jsp" method="POST">               
                    <input type ="submit" name="btn_upload" id="btn_upload" value ="upload"> 
                </form> 
            </div>
        </div>
    
</html>
