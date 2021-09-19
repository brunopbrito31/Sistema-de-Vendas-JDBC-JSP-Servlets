<%-- 
    Document   : index
    Created on : 07/09/2021, 05:10:08
    Author     : bruno
--%>

<%@page import="java.time.Instant"%>
<%@page import="java.util.Date"%>
<%@page import="tools.Auxiliar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administração</title>
        <link rel="stylesheet" type="text/css" href="assets/gerente.css">
    </head>
    <body>
        <% out.print("<div>"+Auxiliar.retornarDataPadrao   (Date.from(Instant.now()))+"</div><br>"); %>
        <h1>Olá <% out.print(request.getParameter("username")); %>, seja bem vindo!</h1>
    </body>
</html>
