/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.estoque;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.entities.ItemEstoque;
import services.EstoqueService;

/**
 *
 * @author bruno
 */
@WebServlet(name = "Estoque", urlPatterns = {"/Estoque"})
public class Estoque extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cod = request.getParameter("cod");
        String tip = request.getParameter("tip-bus").trim();
        EstoqueService estoqueService = new EstoqueService();
        List<ItemEstoque> listaFiltrada = estoqueService.listarEstoqueCompleto(tip, cod);
        StringBuilder sb = new StringBuilder();
        System.out.println("Entrou no get3");
        System.out.println("Valor de cod: "+listaFiltrada);
        if(!listaFiltrada.isEmpty()){
            for(int i = 0; i < listaFiltrada.size(); i++){
                if(i == 0){
                    sb.append("<table>");
                }
                sb.append("<td><img src='").append(listaFiltrada.get(i).getProduto().getCaminhoImagemCompleto()).append("' class='img-prod'><br>").append("Produto: ").append(listaFiltrada.get(i).getProduto().getNome()).append("<br>")
                        .append("Quantidade: ").append(listaFiltrada.get(i).getQuantidade()).append("</td>");
                if(i == (listaFiltrada.size()-1)){
                    sb.append("</table>");
                }
            }
        }else{
            sb.append("Não há resultados para ser exibidos");
        }
        
        String nomp= sb.toString();
        
        request.setAttribute("lista", nomp);
        request.getRequestDispatcher("estoque/pag-estoque.jsp").forward(request, response);
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EstoqueService estoque = new EstoqueService();
        StringBuilder sb = new StringBuilder();
        List<ItemEstoque> listaEstoque = estoque.listarEstoque();
        for(int i = 0; i < listaEstoque.size(); i++){
            if(i == 0){
                sb.append("<table>");
            }
            sb
                    .append("<td><a href ='EstoqueEdiPro?id=").append(listaEstoque.get(i).getId()).append("'><div class ='est_produto'><img src='").append(listaEstoque.get(i).getProduto().getCaminhoImagemCompleto())
                    .append("' class='img-prod' name='").append(listaEstoque.get(i).getProduto().getId()).append("'></a><br>")
                    .append("Produto: ").append(listaEstoque.get(i).getProduto().getNome()).append("<br>")
                    .append("Cod.: ").append(listaEstoque.get(i).getProduto().getCodBarras()).append("<br>")
                    .append("Preço de Custo R$: ").append(String.format("%.2f", listaEstoque.get(i).getProduto().getValorCusto())).append("<br>")
                    .append("Preço de Venda R$: ").append(String.format("%.2f",listaEstoque.get(i).getProduto().getValorVenda())).append("<br>")
                    .append("Vendido por: ").append(listaEstoque.get(i).getProduto().getUnidadeMedida()).append("<br>")
                    .append("Quantidade: ").append(listaEstoque.get(i).getQuantidade()).append("</div></td>");
            if(i == (listaEstoque.size()-1)){
                    sb.append("</table>");
            }
        }
        
        String nomp= sb.toString();
        
        System.out.println("Teste: "+nomp);
        
        request.setAttribute("lista", nomp);
        request.getRequestDispatcher("estoque/pag-estoque.jsp").forward(request, response);
    }
}
