/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.produtos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.enums.NivelAcesso;
import tools.Auxiliar;

/**
 *
 * @author bruno
 */
@WebServlet(name = "ProdCadastro", urlPatterns = {"/ProdCadastro"})
public class ProdCadastro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Metodo get do cadastro de produtos funcionando!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        if(((NivelAcesso)request.getSession().getAttribute("nivelUsuario")).equals(NivelAcesso.ESTOQUISTA)){
            request.getRequestDispatcher("produtos/cadastro-produto.jsp").forward(request, response);
        }else{
            request.setAttribute("msgErro",Auxiliar.getMensagensErro("permissao"));
            request.getRequestDispatcher("auxiliares/pagina-erro.jsp").forward(request, response);
        }
    }
}
