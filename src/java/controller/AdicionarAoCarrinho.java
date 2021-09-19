/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.entities.ItemVenda;
import models.entities.Produto;
import models.entities.dao.FabricaDAO;

/**
 *
 * @author bruno
 */
@WebServlet(name = "AdicionarAoCarrinho", urlPatterns = {"/AdicionarAoCarrinho"})
public class AdicionarAoCarrinho extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("entrou no do post!");
        String idString = request.getParameter("btproduto");
        Integer idSelecionado = Integer.parseInt(idString);
        Produto produtoAdicionado = FabricaDAO.criarProdutoDAO().buscarPeloId(idSelecionado);
        List<ItemVenda> carrinhoAtual = (List<ItemVenda>) request.getSession().getAttribute("carrinhoDeCompras");
        
        if(carrinhoAtual.stream().filter(x -> x.getProduto().getId() == idSelecionado).count() > 0) {
            ItemVenda itemVendaExistente = carrinhoAtual.stream().filter(x -> x.getProduto().getId().equals(idSelecionado)).findFirst().get();
            itemVendaExistente.setQuantidade(itemVendaExistente.getQuantidade()+1);
            itemVendaExistente.setValorTotal(itemVendaExistente.getValorTotal()+produtoAdicionado.getValorVenda());
        }else{
            ItemVenda itemVendaNovo = new ItemVenda();
            itemVendaNovo.setId(null);
            itemVendaNovo.setProduto(produtoAdicionado);
            itemVendaNovo.setQuantidade(1);
            itemVendaNovo.setValorTotal(produtoAdicionado.getValorVenda());
            itemVendaNovo.setVendaOrigem(null);
            carrinhoAtual.add(itemVendaNovo);
        }
        
//        System.out.println("Carrinho de compras ----------------- ");
//        
//        for(ItemVenda x: (List<ItemVenda>)request.getSession().getAttribute("carrinhoDeCompras")){
//            System.out.println("Produto Id: "+x.getProduto().getId()+" Nome: "+x.getProduto().getNome()+" Quantidade: "+x.getQuantidade());
//        }
        

        request.getRequestDispatcher("/vendas/carrinho-de-compras.jsp").forward(request, response);
       
        
    }

}
