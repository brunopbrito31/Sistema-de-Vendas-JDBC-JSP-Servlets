/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.usuarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.entities.ItemVenda;
import models.entities.Usuario;
import models.entities.dao.FabricaDAO;
import models.entities.dao.UsuarioDAO;
import models.enums.NivelAcesso;


@WebServlet(name = "Autenticar", urlPatterns = {"/Autenticar"})
public class Autenticar extends HttpServlet {
    
    UsuarioDAO usuarioDAO = FabricaDAO.criarUsuarioDAO();

//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("username");
        String senha = request.getParameter("userpass");
        boolean teste = usuarioDAO.validarUsuario(nome, senha);
        //request.getSession().setAttribute("ativo", teste); Testar se esse código vai funcionar
        if(teste){
            Usuario usuarioValido = usuarioDAO.buscarUsuario(nome, senha);
            List<ItemVenda> carrinhoDeCompras = new ArrayList<>();
            request.getSession(true).setAttribute("nomeUsuario", nome);
            request.getSession(true).setAttribute("carrinhoDeCompras", carrinhoDeCompras);
            request.getSession(true).setAttribute("idUsuario",usuarioValido.getId());
            switch (usuarioValido.getNivel()) {
                case ESTOQUISTA:
                    request.getSession().setAttribute("nivelUsuario", NivelAcesso.ESTOQUISTA);
                    request.getRequestDispatcher("usuarios/estoquista/estoquista-index.jsp").forward(request, response);
                    break;
                case VENDEDOR:
                    request.getSession().setAttribute("nivelUsuario", NivelAcesso.VENDEDOR);
                    request.getRequestDispatcher("usuarios/vendedor/vendedor-index.jsp").forward(request, response);
                    break;
                default:
                    request.getSession().setAttribute("nivelUsuario", NivelAcesso.GERENTE);
                    request.getRequestDispatcher("usuarios/gerente/gerente-index.jsp").forward(request, response);
                    break;
            }
        }else{
            //response.sendRedirect("index.html?");
            //setar a url com os parametros, fazer a verificacao deles logo na chegada, no welcome do js
            //request.getRequestDispatcher("falha.jsp").forward(request, response);
            request.getRequestDispatcher("index.html").forward(request, response);
            
            
            
        }
        
    }
    
    
    
    



}
