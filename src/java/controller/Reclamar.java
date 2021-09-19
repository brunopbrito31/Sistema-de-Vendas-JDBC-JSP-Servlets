/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tools.GeradorEmail;

/**
 *
 * @author bruno
 */
@WebServlet(name = "Reclamar", urlPatterns = {"/Reclamar"})
public class Reclamar extends HttpServlet {

    @Override @EJB
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String assunto = request.getParameter("assunto");
        System.out.println("Assunto = "+assunto);
        String email = request.getParameter("email");
        System.out.println("Email = "+email);
        String conteudo = request.getParameter("reclamacao");
        System.out.println("Reclamação = "+conteudo);
        //GeradorEmail.enviarEmail(email, conteudo,assunto); implementar com método atualizado
        request.getRequestDispatcher("emailenviado.jsp").forward(request, response);
    }
}
