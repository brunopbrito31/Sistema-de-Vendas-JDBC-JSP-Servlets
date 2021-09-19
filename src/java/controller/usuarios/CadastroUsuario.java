/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.usuarios;

import db.DbException;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.entities.Usuario;
import models.entities.dao.FabricaDAO;
import models.entities.dao.UsuarioDAO;
import models.enums.NivelAcesso;

/**
 *
 * @author bruno
 */
@WebServlet(name = "CadastroUsuario", urlPatterns = {"/CadastroUsuario"})
public class CadastroUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nomeusu");
        String senha = request.getParameter("senhausu");
        String confirSenha = request.getParameter("repsenhausu");
        String email = request.getParameter("emailusu");
        String pergunta = request.getParameter("perguntausu");
        String resposta = request.getParameter("respostausu");
        String nivel = request.getParameter("nivelusu"); // Vem como string numero
        
        Integer nivelAux = Integer.parseInt(nivel);
        
        System.out.println("Testando o valor do nível aqui: "+nivelAux);
        
        if(nivelAux.equals(2)){
            request.getRequestDispatcher("autenticacaocadasusu.jsp").forward(request, response);
        }else{
            UsuarioDAO usuarioDAO = FabricaDAO.criarUsuarioDAO();
            if(senha.trim().equals(confirSenha.trim())){
                // redireciona para página de erro
            }
            List<Usuario> usuariosExistentesComFiltro = usuarioDAO.buscarTodosOsUsuarios(nome);
            if(usuariosExistentesComFiltro.size() > 0){
                // exibe mensagem de erro e volta para a pagina anterior
            }
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setSenha(senha);
            usuario.setEmail(email);
            usuario.setPerguntaCadas(pergunta);
            usuario.setRespostaCadas(resposta);
            usuario.setNivel(NivelAcesso.recuperarNivel(nivelAux));
            try{
                usuarioDAO.inserir(usuario);
            }catch(DbException e){
                request.getRequestDispatcher("falha.jsp").forward(request, response);
            }finally{
                request.getRequestDispatcher("sucesso.jsp").forward(request, response);
            }
            
        }
        

                
    }
}
