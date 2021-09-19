/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.usuarios;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.entities.Usuario;
import models.entities.dao.FabricaDAO;
import models.entities.dao.UsuarioDAO;
import tools.GeradorEmail;

/**
 *
 * @author bruno
 */
@WebServlet(name = "EnviarNovaSenha", urlPatterns = {"/EnviarNovaSenha"})
public class EnviarNovaSenha extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("busUs");
        UsuarioDAO usuarioDAO = FabricaDAO.criarUsuarioDAO();
        List<Usuario> usuariosEncontrados = usuarioDAO.buscarTodosOsUsuarios(nome);
        if(usuariosEncontrados.size() == 1){
            //pagina de usuario existente, para digitar o email
            String email = request.getParameter("emailenv");
            String senha = usuariosEncontrados.get(0).getSenha().trim();
            try(PrintWriter out = response.getWriter()){
                String conteudo = "  Olá "+nome+" você fez uma solicitação recentemente de recuperação de senha"+"\n"+
                        "Sua senha é: "+senha;
                //GeradorEmail.enviarEmail(email,conteudo , "Recuperação de senha do sistema de vendas smart"); implementar com as mudanças
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Reenvio de Senha</title>"); 
//                out.println("<link rel='stylesheet' type='text/css' href='assets/consultar.css'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Senha Enviada para o seu email com Sucesso!</h1>");
                out.println("</body>");
                out.println("</html>");
            }catch(RuntimeException e){
//                request.getRequestDispatcher("falha.jsp").forward(request, response);
                try(PrintWriter out = response.getWriter()){
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Falha na recuperação da senha</title>"); 
    //                out.println("<link rel='stylesheet' type='text/css' href='assets/consultar.css'>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Houve um erro na tentativa de recuperação da senha<h1><br> <p>"+e.getMessage()+"</p>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        }else{
            request.getRequestDispatcher("falha.jsp").forward(request, response);
        }
    }
}
