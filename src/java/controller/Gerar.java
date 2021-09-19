/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.entities.dao.FabricaDAO;
import tools.GeradorPDF;

/**
 *
 * @author bruno
 */
@WebServlet(name = "Gerar", urlPatterns = {"/Gerar"})
public class Gerar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Gerando pdf");
        GeradorPDF.gerarPdfClientes(FabricaDAO.criarClienteDAO().buscarTodosOsClientes(), request.getParameter("autor"));
        request.getRequestDispatcher("rel_clientes_cadas.pdf");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Gerando pdf");
        GeradorPDF.gerarPdfClientes(FabricaDAO.criarClienteDAO().buscarTodosOsClientes(), request.getParameter("autor"));
        request.getRequestDispatcher("rel_clientes_cadas.pdf");
    }

}
