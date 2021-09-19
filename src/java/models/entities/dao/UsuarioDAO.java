/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities.dao;

import java.util.List;
import models.entities.Usuario;

/**
 *
 * @author bruno
 */
public interface UsuarioDAO {
    
    void inserir(Usuario usuario);
    
    void atualizar(Usuario usuario);
    
    void deletarPeloId(int id);
    
    Usuario buscarPeloId(int id);
    
    List<Usuario> buscarTodosOsUsuarios();
    
    boolean validarUsuario(String nome,String senha);
    
    List<Usuario> buscarTodosOsUsuarios(String compl);
    
    Usuario buscarUsuario(String nome, String senha);
    
}
