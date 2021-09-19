/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.produtos;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.entities.Categoria;
import models.entities.Produto;
import models.entities.dao.FabricaDAO;
import models.entities.dao.ProdutoDAO;
import models.enums.StatusCadastro;
import models.enums.UnidadeMedida;

/**
 *
 * @author bruno
 */
@WebServlet(name = "EdicaoProduto", urlPatterns = {"/EdicaoProduto"})
public class EdicaoProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //lógica para os botões editar e excluir, será decidido através de if aqui dentro, pegando pelo nome do botão
        ProdutoDAO produtoDAO = FabricaDAO.criarProdutoDAO();
        List<Produto> produtosCadastrados = produtoDAO.buscarTodosOsProdutosCFiltro("", "", "","0");
        request.setAttribute("produtosCad", produtosCadastrados);
        request.setAttribute("filtroNome", "");
        request.setAttribute("filtroCodB", "");
        request.setAttribute("paginaAtual", 0);
        
        String operacao = request.getParameter("bt-prod-ed");
        
        //Remoção de produto -> Implementar o atributo status, em que o botão de desativar irá setar o status do produto como inativo , tornando assim, o cadastro do mesmo
        //como inativo
        Produto produto = produtoDAO.buscarPeloId(Integer.parseInt(request.getParameter("id-prod")));
        String nome = request.getParameter("nome-prod");
        String cod = request.getParameter("codB-prod");
        double vlrCus = Double.parseDouble(request.getParameter("vlrC-prod"));
        double vlrVen = Double.parseDouble(request.getParameter("vlrV-prod"));
        UnidadeMedida unidade = UnidadeMedida.recuperarUnidade(Integer.parseInt(request.getParameter("sele-u")));
        Categoria categoria = FabricaDAO.criarCategoriaDAO().buscarPeloId(Integer.parseInt(request.getParameter("sele-c")));
        System.out.println("Teste: "+categoria);
        produto.setNome(nome);
        produto.setCodBarras(cod);
        produto.setValorCusto(vlrCus);
        produto.setValorVenda(vlrVen);
        produto.setCategoria(categoria);
        produto.setUnidadeMedida(unidade);
        request.setAttribute("idAtu", produto.getId());
        if(operacao.substring(0, 3).trim().equals("rem")){
            produto.setStatusCadastro(StatusCadastro.INATIVO);
            produtoDAO.atualizar(produto);
            request.setAttribute("scriptRes", "alert('Produto desativado!');");
        }else{
            produtoDAO.atualizar(produto);
            request.setAttribute("scriptRes", "alert('Sucesso!');");
        }
        request.setAttribute("", cod);
        request.getRequestDispatcher("produtos/produtos-cadastrados.jsp").forward(request, response);
    }
}

