<%-- 
    Document   : carrinho-de-compras
    Created on : 09/09/2021, 04:31:48
    Author     : bruno
--%>

<%@page import="models.entities.ItemVenda"%>
<%@page import="java.util.List"%>
<%@page import="tools.Auxiliar"%>
<%@page import="java.util.Date"%>
<%@page import="java.time.Instant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<ItemVenda> carrinhoDeCompras = (List<ItemVenda>)request.getSession().getAttribute("carrinhoDeCompras"); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrinho</title>
        <link rel="stylesheet" type="text/css" href="produtos/assets/catalogo.css">
    </head>
    <body>
        
        <header id="top">
            <% out.print("<div>"+Auxiliar.retornarDataPadrao   (Date.from(Instant.now()))+"</div><br>"); %>
            <h1 id="tit-pes">Carrinho</h1>
            Usuario Atual: <% out.print(request.getSession().getAttribute("nomeUsuario")); %>
        </header><br>
        
        Produtos Adicionados: 
        
        <table id= "tableprodutos">
            <tr class="linhaum">
                <%
                    if(carrinhoDeCompras.isEmpty()){
                        out.println("<h1> Não há produtos no carrinho </h1>");
                    }
                    for(int i = 0; i <carrinhoDeCompras.size(); i++){ //tirar o -1
                        if(i == 5 || i == 0){
                            out.println("<tr>");
                        }
                        
                        out.println("<td class='produto'");
                        out.println("<img src='"+carrinhoDeCompras.get(i).getProduto().getcaminhoImagemCompleto()+"' class='imgproduto'><br>");
                        out.println("<h1 class='nomeproduto'>"+carrinhoDeCompras.get(i).getProduto().getNome()+"</h1><br>");
                        out.println("<div id ='cod-bar-labl'>Cod.:"+carrinhoDeCompras.get(i).getProduto().getCodBarras()+"</div>");
                        out.println("<div id ='quantidade'>Quantidade :"+carrinhoDeCompras.get(i).getQuantidade()+"</div>");
                        out.println("<div class='valor'>Preço R$: "+carrinhoDeCompras.get(i).getValorTotal()+" </div><br>");
                        out.println("</td>");
                        if(i == 10 || i==4){
                            out.println("</tr><br>");
                        }
                    }
                %>
            </tr>
        </table>
    </body>
</html>
