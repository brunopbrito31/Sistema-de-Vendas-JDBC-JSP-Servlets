/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bruno
 */
@WebServlet(name = "ListarProdutos", urlPatterns = {"/ListarProdutos"})
public class ListarProdutos extends HttpServlet {
    
    private String filtro = "";
    private int pagina = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(!request.getParameter("produtopesquisado").equals("")){
            request.setAttribute("produtopesquisado", request.getParameter("produtopesquisado"));
            request.setAttribute("pg","0");
            if(!request.getAttribute("pg").toString().equals("0")){
                request.setAttribute("pg",request.getParameter("pg"));
            }
            System.out.println("Entrou na condição A");
        }else{
            request.setAttribute("produtopesquisado",this.filtro);
            request.setAttribute("pg", request.getParameter("pg"));
            System.out.println("Entrou na condição B");
            request.getRequestDispatcher("produtos/listar-produtos.jsp").forward(request, response);
        }
        request.getRequestDispatcher("produtos/listar-produtos.jsp").forward(request, response); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("produtopesquisado",this.filtro);
        request.setAttribute("pg", this.pagina);
        request.getRequestDispatcher("produtos/listar-produtos.jsp").forward(request, response);
    }
}
