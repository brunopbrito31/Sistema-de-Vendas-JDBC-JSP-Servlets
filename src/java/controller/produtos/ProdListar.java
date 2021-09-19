/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.produtos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.entities.Produto;
import models.entities.dao.FabricaDAO;
import models.entities.dao.ProdutoDAO;

/**
 *
 * @author bruno
 */
@WebServlet(name = "ProdListar", urlPatterns = {"/ProdListar"})
public class ProdListar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Inicialização dos controles de filtro e de paginação
        ProdutoDAO produtoDAO = FabricaDAO.criarProdutoDAO();
        List<Produto> produtosCadastrados = produtoDAO.buscarTodosOsProdutosCFiltro("", "", "","");
        request.setAttribute("produtosCad", produtosCadastrados);
        request.setAttribute("filtroNome", "");
        request.setAttribute("filtroCodB", "");
        request.setAttribute("paginaAtual", 0);
        request.setAttribute("idAtu", "9999");
        request.setAttribute("scriptRes", "alert('Catogo de Produtos')");
        
        request.getRequestDispatcher("produtos/produtos-cadastrados.jsp").forward(request, response);
    }
}
