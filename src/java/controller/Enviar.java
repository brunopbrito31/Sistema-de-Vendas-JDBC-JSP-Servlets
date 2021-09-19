/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tools.ManipuladorArquivo;

/**
 *
 * @author bruno
 */
@WebServlet(name = "Enviar", urlPatterns = {"/Enviar"})
public class Enviar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String arquivoCarregadoAux = request.getParameter("upload");
        System.out.println("Teste: "+arquivoCarregadoAux);
        FileInputStream arquivoCarregado = new FileInputStream(arquivoCarregadoAux);
        ManipuladorArquivo manipuladorArquivo = new ManipuladorArquivo();
        manipuladorArquivo.upload("e:/arquivossalvos", "pao", arquivoCarregado);
        
    }
    
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String arquivoCarregadoAux = request.getParameter("upload");
        String nome = request.getParameter("arqenv");
        nome = nome.trim().concat(".jpg");
        System.out.println("Teste: "+arquivoCarregadoAux);
        FileInputStream arquivoCarregado = new FileInputStream(arquivoCarregadoAux);
        ManipuladorArquivo manipuladorArquivo = new ManipuladorArquivo();
        manipuladorArquivo.upload("C:\\GlassFish Server1\\glassfish5\\glassfish\\domains\\domain1\\config", nome, arquivoCarregado);
        request.getRequestDispatcher("sucessodois.jsp").forward(request, response);
        
    }

}
