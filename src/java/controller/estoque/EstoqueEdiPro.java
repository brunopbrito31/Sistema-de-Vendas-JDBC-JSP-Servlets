/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.estoque;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.entities.ItemEstoque;
import models.entities.dao.FabricaDAO;
import models.entities.dao.ItemEstoqueDAO;

/**
 *
 * @author bruno
 */
@WebServlet(name = "EstoqueEdiPro", urlPatterns = {"/EstoqueEdiPro"})
public class EstoqueEdiPro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        ItemEstoqueDAO itemEstoqueDAO = FabricaDAO.criarItemEstoqueDAO();
        Integer idProcurado = Integer.parseInt(request.getParameter("id"));
        ItemEstoque itemEstoque = itemEstoqueDAO.buscarPeloId(idProcurado);
        sb
                .append("<form action=''>")
                .append("<table>")
                .append("<tr><td>Nome: </td>").append("<td><input type='text' value='").append(itemEstoque.getProduto().getNome()).append("' disabled>").append("</td>").append("</tr>")
                .append("<tr><td>Cod.: </td>").append("<td><input type='text' value='").append(itemEstoque.getProduto().getCodBarras()).append("' disabled>").append("</td>").append("</tr>")
                .append("<tr><td>Unid.: </td>").append("<td><input type='text' value='").append(itemEstoque.getProduto().getUnidadeMedida()).append("' disabled>").append("</td>").append("</tr>")
                .append("<tr><td>Custo R$: </td>").append("<td><input type='text' value='").append(itemEstoque.getProduto().getValorCusto()).append("' disabled>").append("</td>").append("</tr>")
                .append("<tr><td>Preço R$: </td>").append("<td><input type='text' value='").append(itemEstoque.getProduto().getValorVenda()).append("' disabled>").append("</td>").append("</tr>")
                .append("<tr><td>Categoria: </td>").append("<td><input type='text' value='").append(itemEstoque.getProduto().getCategoria().getNome()).append("' disabled>").append("</td>").append("</tr>")
                .append("<tr><td>Quantidade: </td>").append("<td><input type='text' value='").append(itemEstoque.getQuantidade()).append("' disabled>").append("</td>").append("</tr>")
                .append("</table>")
                .append("<button type='submit' name='btQtI' value='addIQt'>Adicionar</button>")
                .append("<button type='submit' name='btQtI' value='remIQt'>Remover</button>")
                .append("</form>");
        String itemEstoqueEncontrado = sb.toString();
        request.setAttribute("edicao", itemEstoqueEncontrado);
        request.getRequestDispatcher("estoque/pag-ed-estoque.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
