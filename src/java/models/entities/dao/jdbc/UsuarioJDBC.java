/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities.dao.jdbc;

import db.Db;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.entities.Usuario;
import models.entities.dao.UsuarioDAO;
import models.enums.NivelAcesso;

/**
 *
 * @author bruno
 */
public class UsuarioJDBC implements UsuarioDAO{
    
    private Connection conn;

    public UsuarioJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Usuario usuario) { // ok
            PreparedStatement st = null;
            try {
                st = conn.prepareStatement(
                    "INSERT INTO tb_usuarios " +
                            "(nome, senha, email, pergunta, resposta, nivel) " +
                            "VALUES " +
                            "(?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getSenha());
            st.setString(3, usuario.getEmail());
            st.setString(4, usuario.getPerguntaCadas());
            st.setString(5, usuario.getRespostaCadas());
            st.setInt(6, usuario.getNivel().ordinal()); // Verificar se condição está indo corretamente 
            
            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    usuario.setId(id);
                }
                Db.closeResultSet(rs);
            } else {
                throw new DbException("Ocorreu um erro. Não houveram linhas afetadas!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public void atualizar(Usuario usuario) { // ok
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
              "UPDATE tb_usuarios " +
                      "SET nome = ?, senha = ?, email = ?, pergunta = ?, resposta = ?, nivel = ? " +
                      "WHERE id = ?");
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getSenha());
            st.setString(3, usuario.getEmail());
            st.setString(4, usuario.getPerguntaCadas());
            st.setString(5, usuario.getRespostaCadas());
            st.setInt(6, usuario.getNivel().ordinal()); // Verificar se condição está ok
            st.setInt(7, usuario.getId());
            
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public void deletarPeloId(int id) { //ok
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM tb_usuarios WHERE id = ?");

            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public Usuario buscarPeloId(int id) { //ok
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT * FROM tb_usuarios " +
                            "WHERE tb_usuarios.id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                return instanciarUsuario(rs);
            }
            return null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
            Db.closeResultSet(rs);
        }
    }
    
    @Override
    public Usuario buscarUsuario(String nome, String senha) { //ok
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT * FROM tb_usuarios " +
                            "WHERE tb_usuarios.nome = ? "
                            + "AND tb_usuarios.senha= ? ");
            st.setString(1, nome);
            st.setString(2, senha);
            
            rs = st.executeQuery();
            if(rs.next()){
                return instanciarUsuario(rs);
            }
            return null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
            Db.closeResultSet(rs);
        }
    }
    
    @Override
    public boolean validarUsuario(String nome, String senha) { //ok
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT * FROM tb_usuarios " +
                            "WHERE tb_usuarios.nome = ?");
            st.setString(1, nome);
            rs = st.executeQuery();
            if(rs.next()){
                Usuario usuarioProcurado = instanciarUsuario(rs);
                if(usuarioProcurado.getSenha().equals(senha)){
                    return true;
                }
            }
            return false;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
            Db.closeResultSet(rs);
        }
    }

    @Override
    public List<Usuario> buscarTodosOsUsuarios() {  //ok
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM tb_usuarios");
            rs = st.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPerguntaCadas(rs.getString("pergunta"));
                usuario.setRespostaCadas(rs.getString("resposta"));
                usuario.setNivel(NivelAcesso.recuperarNivel(rs.getInt("nivel")));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeResultSet(rs);
            Db.closeStatement(st);
        }
    }
    
    
    @Override
    public List<Usuario> buscarTodosOsUsuarios(String compl) { // ok
        PreparedStatement st = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_usuarios "
                            + "WHERE 1=1 ");
        Boolean complemento = !compl.equals("");
        if(complemento){
            sb.append("AND tb_usuarios.nome = ?");
        }
        try {
            st = conn.prepareStatement(sb.toString());
            if(complemento){
                st.setString(1, compl);
            }
            rs = st.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPerguntaCadas(rs.getString("pergunta"));
                usuario.setRespostaCadas(rs.getString("resposta"));
                usuario.setNivel(NivelAcesso.recuperarNivel(rs.getInt("nivel")));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeResultSet(rs);
            Db.closeStatement(st);
        }
    }
    
    
    private static Usuario instanciarUsuario(ResultSet rs){ // ok
        Usuario usuario = new Usuario();
        try{
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setEmail(rs.getString("email"));
            usuario.setPerguntaCadas(rs.getString("pergunta"));
            usuario.setRespostaCadas(rs.getString("resposta"));
            usuario.setNivel(NivelAcesso.recuperarNivel(rs.getInt("nivel")));
            return usuario;
        }catch (SQLException e){
        throw new DbException("Error: "+e.getMessage());
        }
    }
    
}
