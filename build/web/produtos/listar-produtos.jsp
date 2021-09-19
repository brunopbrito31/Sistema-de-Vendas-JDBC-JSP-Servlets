<%-- 
    Document   : listar-produtos
    Created on : 07/09/2021, 07:01:02
    Author     : bruno
--%>
<%@page import="java.net.URL"%>
<%@page import="java.time.Instant"%>
<%@page import="tools.Auxiliar"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="models.entities.Produto"%>
<%@page import="models.entities.dao.ProdutoDAO"%>
<%@page import="models.entities.dao.FabricaDAO"%>
<% ProdutoDAO produtoDAO = FabricaDAO.criarProdutoDAO(); %>
<% 
   String filtroPesquisa = request.getAttribute("produtopesquisado").toString();
   List<Produto> produtosCatalogo = produtoDAO.buscarTodosOsProdutosCFiltro(filtroPesquisa,"","");

   int paginaAtual = Integer.parseInt(request.getAttribute("pg").toString());
 
   int qtPag = Auxiliar.rertornarQtdPaginas(produtosCatalogo.size());
   List<Produto> listaPaginada;
   if(!request.getAttribute("produtopesquisado").toString().equals("")){
       listaPaginada = produtosCatalogo;
   }else{
       listaPaginada = Auxiliar.gerarListaPaginada(produtosCatalogo, paginaAtual, 10);
   }
%>
   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogo de Produtos</title>
        <link rel="stylesheet" type="text/css" href="produtos/assets/catalogo.css">
    </head>
    <body>
        <header id="top">
            <% out.print("<div>"+Auxiliar.retornarDataPadrao   (Date.from(Instant.now()))+"</div><br>"); %>
            <h1 id="tit-pes">Catálogo de Produtos</h1>
            Usuario Atual: <% out.print(request.getSession().getAttribute("nomeUsuario")); %>
        </header><br>
        
         <form method="GET" id="buscapeloproduto">
            <input type="text" id="pesquisarproduto" name="produtopesquisado" placeholder="Pesquisar produto">
            <button type="submit" name="btpesquisarproduto" id="btpesquisarproduto"><img src="produtos/imgs/pesquisa.jpg" id="img-pesquisa"></button>
        </form>
            
        <table id= "tableprodutos">
            <tr class="linhaum">
                <%
                    if(listaPaginada.isEmpty()){
                        out.println("<h1> Sua Pesquisa não retornou resultados </h1>");
                    }
                    for(int i = 0; i <listaPaginada.size(); i++){ //tirar o -1
                        if(i == (listaPaginada.size()/2) || i == 0){
                            out.println("<tr>");
                        }
                        out.println("<td class='produto'>");
                        out.println("<form action='AdicionarAoCarrinho' method='POST' id='"+listaPaginada.get(i).getNome()+"'>");
                        out.println("<img src='"+listaPaginada.get(i).getCaminhoImagemCompleto()+"' class='imgproduto'><br>");
                        out.println("<h1 class='nomeproduto' name='nomProd'>"+listaPaginada.get(i).getNome()+"</h1><br>");
                        out.println("<div id ='cod-bar-labl'>Cod.: "+listaPaginada.get(i).getCodBarras()+"</div>");
                        out.println("<div id ='cod-bar-labl'>Categoria: "+listaPaginada.get(i).getCategoria().getNome()+"</div>");
                        out.println("<div class='precoproduto'>Preço R$: "+listaPaginada.get(i).getValorVenda()+" </div><br>");
                        out.println("<div class='quantidadeproduto' name='sele'>Quantidade:<select class='select-produto'>");
                        out.println("<option value='0' name='sele'>0</option>");
                        out.println("<option value='1' name='sele'>1</option>");
                        out.println("<option value='2' name='sele'>2</option>");
                        out.println("</select></div><br>");
                        out.println("<button type='submit' name='btproduto' class='botao-produto' value='"+listaPaginada.get(i).getId()+"'>Adicionar</button>");
                        out.println("</form>");
                        out.println("</td>");
                        if(i == listaPaginada.size() || i == ((listaPaginada.size()/2)-1)){
                            out.println("</tr><br>");
                        }
                    }
                %>
            </tr>
        </table>
        
        <footer>
            <table>
                <tr>
                    <%
                        for(int i = 0; i <qtPag; i++){
                            out.print("<div id = 'bt-box-botton'>");
                            out.print("<form action='ListarProdutos' id ='form-bot' method='GET'");
                            out.print("<td><button type='submit' class= 'btPag' name='pg' value='"+i+"'>"+(i+1)+"</button></td>");
                            out.print("</form>");
                            out.print("</div>");
                        }
                    %>
                </tr>
            </table>
        </footer>
    </body>
</html>
