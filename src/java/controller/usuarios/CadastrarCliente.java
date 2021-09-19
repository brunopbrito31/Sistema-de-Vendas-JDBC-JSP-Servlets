/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.usuarios;

import db.DbException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.entities.Cliente;
import models.entities.dao.ClienteDAO;
import models.entities.dao.FabricaDAO;

/**
 *
 * @author bruno
 */
@WebServlet(name = "CadastrarCliente", urlPatterns = {"/CadastrarCliente"})
public class CadastrarCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entrou no método dopost");
        try(PrintWriter out = response.getWriter()){
            System.out.println("Entrou no try");
            ClienteDAO clienteDAO = FabricaDAO.criarClienteDAO();
            Cliente cliente = new Cliente();
            cliente.setNome(request.getParameter("nome"));
            cliente.setIdade(request.getParameter("idade"));
            cliente.setPeso(request.getParameter("peso"));
            System.out.println("Cliente formado: "+cliente);
            clienteDAO.inserir(cliente);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cadastro Sucesso</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Cadastro do cliente: " + cliente.getNome() + " realizado com sucesso!</h1>");
            out.println("</body>");
            out.println("</html>");
            
                
        }catch(DbException e){
            try(PrintWriter out = response.getWriter()){ // Não está funcionando 
                  
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Cadastro Falha</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Houve uma falha no cadastro</h1>");
                out.println("<h1>Error: "+e.getMessage()+"</h1>");
                out.println("</body>");
                out.println("</html>");
            }
                
        }

        
    }


}
