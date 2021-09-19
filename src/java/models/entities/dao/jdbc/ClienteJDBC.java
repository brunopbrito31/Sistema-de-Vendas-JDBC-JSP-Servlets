/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities.dao.jdbc;

import com.mysql.jdbc.Connection;
import db.Db;
import db.DbException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.entities.Cliente;
import models.entities.dao.ClienteDAO;

/**
 *
 * @author bruno
 */
public class ClienteJDBC implements ClienteDAO{
    
    private Connection conn;

    public ClienteJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Cliente cliente) {
            PreparedStatement st = null;
            try {
                st = conn.prepareStatement(
                    "INSERT INTO tb_clientes " +
                            "(nome, idade, peso) " +
                            "VALUES " +
                            "(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getIdade());
            st.setString(3, cliente.getPeso());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    Integer id = rs.getInt(1);
                    cliente.setId(id);
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
    public void atualizar(Cliente cliente) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
              "UPDATE tb_clientes " +
                      "SET nome = ?, idade = ?, peso = ? " +
                      "WHERE id = ?");
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getIdade());
            st.setString(3, cliente.getPeso());
            
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public void deletarPeloId(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM tb_clientes WHERE id = ?");

            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public Cliente buscarPeloId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT * FROM tb_clientes " +
                            "WHERE tb_clientes.id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                return instanciarCliente(rs);
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
    public List<Cliente> buscarTodosOsClientes() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM tb_clientes");
            rs = st.executeQuery();
            List<Cliente> clientes = new ArrayList<>();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setIdade(rs.getString("idade"));
                cliente.setPeso(rs.getString("peso"));
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeResultSet(rs);
            Db.closeStatement(st);
        }
    }
    
    
    private static Cliente instanciarCliente(ResultSet rs){
        Cliente cliente = new Cliente();
        try{
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setIdade(rs.getString("idade"));
            cliente.setPeso(rs.getString("peso"));
            return cliente;
        }catch (SQLException e){
        throw new DbException("Error: "+e.getMessage());
        }
    }
    
    
}
