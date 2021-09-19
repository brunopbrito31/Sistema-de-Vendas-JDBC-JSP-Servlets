/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.produtos;

import db.DbException;
import java.io.IOException;
import java.io.PrintWriter;
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
import tools.Auxiliar;

/**
 *
 * @author bruno
 */
@WebServlet(name = "ProdValCadastro", urlPatterns = {"/ProdValCadastro"})
public class ProdValCadastro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codBarras = request.getParameter("prodCodB"); // validar se já há algum produto com o mesmo código de barras cadastrado
        ProdutoDAO produtoDAO = FabricaDAO.criarProdutoDAO();
        List<Produto> produtosEncontrados = produtoDAO.buscarTodosOsProdutosCFiltro("", codBarras,"","");
        if(!produtosEncontrados.isEmpty()){
            request.setAttribute("msgErro", Auxiliar.getMensagensErro("conflitoCodBarras"));
            request.getRequestDispatcher("auxiliares/pagina-erro.jsp").forward(request, response);
        }
        String nome = request.getParameter("prodNome");
        String vlrCusto = request.getParameter("prodVlCs");
        String vlrVenda = request.getParameter("prodVlVd");
        String imagem = nome.toLowerCase().concat(".jpg");
        int categoriaId = Integer.parseInt(request.getParameter("prodCate"));
        Categoria categoria = FabricaDAO.criarCategoriaDAO().buscarPeloId(categoriaId);
        Produto produtoASerSalvo = new Produto();
        try{
            produtoASerSalvo.setNome(nome);
            produtoASerSalvo.setCodBarras(codBarras);
            produtoASerSalvo.setImagem(imagem);
            produtoASerSalvo.setValorCusto(Double.parseDouble(vlrCusto));
            produtoASerSalvo.setValorVenda(Double.parseDouble(vlrVenda)); 
            produtoASerSalvo.setCategoria(categoria);
            produtoASerSalvo.setUnidadeMedida(UnidadeMedida.recuperarUnidade(Integer.parseInt(request.getParameter("prodUnid"))));
            produtoASerSalvo.setStatusCadastro(StatusCadastro.ATIVO);
            produtoDAO.inserir(produtoASerSalvo);
        }catch(DbException e){
            request.setAttribute("msgErro", Auxiliar.getMensagensErro("erroDeGravacaoBanco"));
            request.getRequestDispatcher("auxiliares/pagina-erro.jsp").forward(request, response);
        }
        
        PrintWriter  out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        out.println("<title>Sucesso no cadastro</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"produtos/assets/cadastro.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id='card-cadast'>");
        out.println("<div class='card-cadast-tit'>");
        out.println("SUCESSO!Dados do Produto cadastrado******************* <br>");
        out.println("Id: "+produtoASerSalvo.getId()+"<br>");
        out.println("Nome: "+nome+"<br>"); 
        out.println("Cod.: "+codBarras+"<br>");
        out.println("Unid: "+UnidadeMedida.recuperarUnidade(Integer.parseInt(request.getParameter("prodUnid"))).toString()+"<br>");
        out.println("Categoria: "+produtoASerSalvo.getCategoria().getNome()+"<br>");
        out.println("Valor Custo R$:"+vlrCusto+"<br>");
        out.println("Valor Venda R$:"+vlrVenda+"<br>");
        out.println("</div>");
        out.println("<img src=\"./CarregadorImagem?path="+imagem+"\"/ id='img-prod-cadas'><br>");
        out.println("<div class class='rod-prod-cadas'>");
        out.println("******************************************************");
        out.println("<a href='produtos/cadastro-produto.jsp'>Ok</a>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        
        //Histórico de tentativas, implementar o upload
        
//        String imagem = request.getParameter("prodImag").toString();
//        
//        PrintWriter  out = response.getWriter();
//        
//        out.println("<!DOCTYPE html>");
//        out.println("<html>");
//        out.println("<head>");
//        out.println("<title> Resultado </title>");
//        out.println("</head>");
//        out.println("<body>");
//        out.println("Nome: "+nome+"<br>");
//        out.println("Código de barras: "+codBarras+"<br>");
//        out.println("Valor Custo: "+vlrCusto+"<br>");
//        out.println("Valor Venda: "+vlrVenda+"<br>");
//        out.println("Imagem: "+"<img src=\"./CarregadorImagem?path="+imagem+"\"/>");
//        
//        out.println("</body>");
//        out.println("</html>");
//        
//        
//        System.out.println("Dado trazido na imagem: "+imagem);
//        //informação da imagem
//        
        
    }
}
