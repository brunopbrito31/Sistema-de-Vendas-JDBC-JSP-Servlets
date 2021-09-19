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
import models.entities.Categoria;
import models.entities.dao.CategoriaDAO;

/**
 *
 * @author bruno
 */
public class CategoriaJDBC implements CategoriaDAO {
    
    private Connection conn;

    public CategoriaJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Categoria categoria) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO tb_categorias " +
                            "(nome ) " +
                            "VALUES " +
                            "(?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, categoria.getNome());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    Integer id = rs.getInt(1);
                    categoria.setId(id);
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
    public void atualizar(Categoria categoria) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
              "UPDATE tb_categorias " +
                      "SET nome = ? " +
                      "WHERE id = ?");
            st.setString(1, categoria.getNome());
            st.setInt(2, categoria.getId());
            
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        }; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletarPeloId(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM tb_categorias WHERE id = ?");

            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public Categoria buscarPeloId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT * FROM tb_categorias " +
                            "WHERE tb_categorias.id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                return instanciarCategoria(rs);
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
    public List<Categoria> buscarTodasAsCategorias() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM tb_categorias");
            rs = st.executeQuery();
            List<Categoria> categorias = new ArrayList<>();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                categorias.add(categoria);
            }
            return categorias;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeResultSet(rs);
            Db.closeStatement(st);
        }
    }
    
    private static Categoria instanciarCategoria(ResultSet rs){
        Categoria categoria = new Categoria();
        try{
            categoria.setId(rs.getInt("id"));
            categoria.setNome(rs.getString("nome"));
            return categoria;
        }catch (SQLException e){
        throw new DbException("Error: "+e.getMessage());
        }
    }
}
