<%-- 
    Document   : cadastro-produto
    Created on : 11/09/2021, 12:29:50
    Author     : bruno
--%>

<%@page import="java.util.List"%>
<%@page import="models.entities.Categoria"%>
<%@page import="models.entities.dao.CategoriaDAO"%>
<%@page import="models.entities.dao.FabricaDAO"%>
<% request.setAttribute("prodNome",""); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Produto</title>
    </head>
    <body>
        <h1>Cadastro de Produto</h1>
        <!-- Para incrementar o upload do formulário, inserir dentro da tag abaixo =  enctype="multipart/form-data"-->
        <form action="ProdValCadastro" method="POST">
            <table>
                <tr id="tb_prod_cadas_l1">
                    <td>Nome: </td>
                    <td><input type="text" name="prodNome"></td>
                </tr>
                <tr>
                    <td>Cod.Barras:</td>
                    <td><input type="text" name="prodCodB"></td>
                </tr>
                <tr>
                    <td>Vlr Custo R$: </td>
                    <td><input type="text" name="prodVlCs"></td>
                </tr>
                <tr>
                    <td>Vlr Venda R$: </td>
                    <td><input type="text" name="prodVlVd"></td>
                </tr>
                <tr>
                    <td>Categoria: </td>
                    <td>
                        <select name="prodCate">
                            <%
                                CategoriaDAO categoriaDAO = FabricaDAO.criarCategoriaDAO();
                                List<Categoria> categorias = categoriaDAO.buscarTodasAsCategorias();
                                for(Categoria x: categorias){
                                    out.print("<option value = '"+x.getId()+"'>"+x.getNome()+"");
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Unidade de Medida: </td>
                    <td>
                        <select name="prodUnid">
                            <option name="prodUnid" value="0">UNIDADE</option>
                            <option name="prodUnid" value="1">KG</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Imagem: </td>
                    <td><input type="file" name="prodImag"></td> <!-- mudar para file -->
                </tr>
            </table>
            <button type="submit">Cadastrar</button>
        </form>
    </body>
</html>
