<%-- 
    Document   : produtos-cadastrados
    Created on : 12/09/2021, 01:59:21
    Author     : bruno
--%>
<%@page import="models.entities.Categoria"%>
<%@page import="models.entities.dao.ProdutoDAO"%>
<%@page import="models.entities.dao.FabricaDAO"%>
<%@page import="models.entities.Produto"%>
<%@page import="java.util.List"%>
<%
    int idAtu = Integer.parseInt(request.getAttribute("idAtu").toString());
    ProdutoDAO produtoDAO = FabricaDAO.criarProdutoDAO();
    int pagina = Integer.parseInt(request.getAttribute("paginaAtual").toString());
    String filtroNom = request.getAttribute("filtroNome").toString();
    String filtroCod = request.getAttribute("filtroCodB").toString();
    List<Produto> listaDeProdutos = produtoDAO.buscarTodosOsProdutosCFiltro(filtroNom, filtroCod,"","0"); 
    List<Categoria> categorias = FabricaDAO.criarCategoriaDAO().buscarTodasAsCategorias();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catálogo</title>
        <link rel="stylesheet" type="text/css" href="produtos/assets/listagem.css">
        
    </head>
    <body>
        <script>
            <% out.print(request.getAttribute("scriptRes")); %>
        </script>
        <div id='topo'>
        <h1 id='tit'>Produtos Cadastrados</h1>
        <div>
            <form action='ProdCadastro' method='POST'>
                <button type='submit' id='bt-prod-btn' name='bt-prod-cadn' value='pesquisar'>Cadastrar Novo Produto</button>
            </form><br>
            <form method="GET" id="buscapeloproduto">
                <input type="text" id="pesquisarproduto" name="produtopesquisado" placeholder="Pesquisar produto">
                Filtrar por: <select id="selectfil">
                    <option value="1">Nome</option>
                    <option value="2">Codigo de Barras</option>
                    <option value="3">Categoria</option>
                </select>
                <button type="submit" name="btpesquisarproduto" id="btpesquisarproduto"><img src="produtos/imgs/pesquisa.jpg" id="img-pesquisa"></button>
            </form><br>
        </div>
        </div><br>
        <ul>
            <%
                for(int i = 0; i <5; i++){
                    if(listaDeProdutos.get(i).getId().equals(idAtu)){
                        out.print("<div id='resup'>Produto Editado</div>");
                        out.print("<li class='prod' id='"+listaDeProdutos.get(i).getId()+"' style='background-color: green;' ' >");
                    }else{
                        out.print("<li class='prod' id='"+listaDeProdutos.get(i).getId()+"'>");
                    }
                    out.print("<form action='EdicaoProduto' method='POST' id='form-princ'>");
                    out.print("<tr>");
                    out.print("<td><img src='"+listaDeProdutos.get(i).getCaminhoImagemCompleto()+"' class='img-lista'></td>");
                    out.print("<td class='no-prod'>Id: </td><td><input type='text' value='"+listaDeProdutos.get(i).getId()+"' readonly name='id-prod' class='in-prod'  id='iddprod'></td>");
                    out.print("<td cllass='no-prod'>Nome: </td><td><input type='text' value='"+listaDeProdutos.get(i).getNome()+"' name='nome-prod' class='in-prod'></td>");
                    out.print("<td class='no-prod'>Categoria: </td><td><select name='sele-c' class='in-prod'>");
                    for(Categoria x: categorias){
                        if(x.getId().equals(listaDeProdutos.get(i).getCategoria().getId())){
                           out.println("<option value='"+x.getId()+"' name='sele-c' class='cc-prod' selected>"+x.getNome()+"</option>"); 
                        }else{
                           out.println("<option value='"+x.getId()+"' name='sele-c' class='cc-prod'>"+x.getNome()+"</option>");
                        }
                    }
                    out.print("</select></td>");
                    // implementar unidade de medida e status aqui
                    out.print("<td class='no-prod'>Unidade de Medida: </td><td><select name='sele-u' class='in-prod'>");
                    if(listaDeProdutos.get(i).getUnidadeMedida().ordinal() == 0){
                        out.print("<option value ="+0+" name='sele-u' class='cc-prod' selected>UNID.</option>");
                        out.print("<option value ="+1+" name='sele-u' class='cc-prod'>KG</option>");
                    }else{
                        out.print("<option value ="+0+" name='sele-u' class='cc-prod'>UNID.</option>");
                        out.print("<option value ="+1+" name='sele-u' class='cc-prod' selected>KG</option>");
                    }
                    out.print("</select></td>");
                   
                    // implementar unidade de medida e status aqui
//                    out.print("<td class='no-prod'>Status do Cadastro: </td><td><select name='sele-s' class='in-prod'>");
//                    if(listaDeProdutos.get(i).getStatusCadastro().ordinal() == 0){
//                        out.print("<option value ="+0+" name='sele-s' class='cc-prod' selected>ATIVO.</option>");
//                        out.print("<option value ="+1+" name='sele-s' class='cc-prod'>INATIVO</option>");
//                    }else{
//                        out.print("<option value ="+0+" name='sele-s' class='cc-prod'>ATIVO.</option>");
//                        out.print("<option value ="+1+" name='sele-s' class='cc-prod' selected>INATIVO</option>");
//                    }
//                    out.print("</select></td>");
                    out.print("<td class='co-prod'>Cód.: </td><td><input type='text' value='"+listaDeProdutos.get(i).getCodBarras()+"' name='codB-prod' class='in-prod'></td>");
                    out.print("<td class='vc-prod'>Valor de Custo R$: </td><td><input type='text' value='"+listaDeProdutos.get(i).getValorCusto()+"' name='vlrC-prod' class='in-prod'></td>");
                    out.print("<td class='vv-prod'>Valor de Venda R$: </td><td><input type='text' value='"+listaDeProdutos.get(i).getValorVenda()+"' class='in-prod' name='vlrV-prod'></td>");
                    out.print("<td>");
                    out.print("<button type='submit' id='bt-ed' class='bt-prod-bt' name='bt-prod-ed' value='editar' onclick='simbor();'>Editar</button>");
                    out.print("</td>");
                    out.print("<td>");
                    out.print("<button type='submit' id='bt-re' class='bt-prod-bt' name='bt-prod-ed' value='remover' onclick='simbor2();'>Remover</button>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("</form>");
                    out.print("</li>");
                } 
            %>
        </ul>
    </body>
</html>
