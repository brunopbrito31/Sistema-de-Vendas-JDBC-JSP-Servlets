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
import models.entities.Movimentacao;
import models.enums.TipoMovimentacao;
import models.entities.dao.MovimentacaoDAO;

/**
 *
 * @author bruno
 */
public class MovimentacaoJDBC implements MovimentacaoDAO {
        
    private Connection conn;

    public MovimentacaoJDBC(Connection conn) {
        this.conn = conn;
    }
    // Insercao
    private final String queryInsercaoMovimentacacao =  "INSERT INTO tb_movimentacoes " +
                                                        "(desc_mov, data_mov, tipo_mov) " +
                                                        "VALUES " +
                                                        "(?, ?, ?)";
    
    private final String erroInsercaoMovimentacao = "Ocorreu um erro. Não houveram linhas afetadas!";
    
    // Atualizacao
    private final String queryAtualizacaoMovimentacao = "UPDATE tb_movimentacoes " +
                                                        "SET desc_mov = ?, data_mov = ?, tipo_mov = ? " +
                                                        "WHERE id = ?";
    
    // Exclusao
    private final String queryExclusaoMovimentacao = "DELETE FROM tb_movimentacoes WHERE id = ?";
    
    // Buscas
    private final String queryBuscaTdsMovimentacoes = "SELECT * FROM tb_movimentacoes "
                                                    + "WHERE 1=1";
    
    private final String queryBuscarMovimentacaoPeloId = queryBuscaTdsMovimentacoes.concat(" AND tb_movimentacoes.id = ?");
    
    private final String queryBuscarMovimentacaoPeloTipo = queryBuscaTdsMovimentacoes.concat(" AND tb_movimentacoes.tipo_mov = ?");
    
    @Override
    public void inserir(Movimentacao movimentacao) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                queryInsercaoMovimentacacao,
                Statement.RETURN_GENERATED_KEYS);
            
            inserirAtributosBasicosMovimentacao(movimentacao,st);

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    Integer id = rs.getInt(1);
                    movimentacao.setId(id);
                }
                Db.closeResultSet(rs);
            } else {
                throw new DbException(erroInsercaoMovimentacao);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public void atualizar(Movimentacao movimentacao) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(queryAtualizacaoMovimentacao);
            inserirAtributosBasicosMovimentacao(movimentacao,st);
            st.setDouble(4, movimentacao.getId());
            
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
            st = conn.prepareStatement(queryExclusaoMovimentacao);

            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public Movimentacao buscarPeloId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(queryBuscarMovimentacaoPeloId);
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Movimentacao movimentacao = instanciarMovimentacao(rs);
                return movimentacao;
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
    public Movimentacao buscarPeloTipo(TipoMovimentacao tipo) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(queryBuscarMovimentacaoPeloTipo);
            st.setInt(1, tipo.ordinal());
            rs = st.executeQuery();
            if(rs.next()){
                Movimentacao movimentacao = instanciarMovimentacao(rs);
                return movimentacao;
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
    public List<Movimentacao> buscarTodasAsMovimentacoes() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(queryBuscaTdsMovimentacoes);
            rs = st.executeQuery();
            List<Movimentacao> movimentacoes = new ArrayList<>();

            while (rs.next()) {
                Movimentacao movimentacaoAtual = instanciarMovimentacao(rs);
                movimentacoes.add(movimentacaoAtual);
            }
            return movimentacoes;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeResultSet(rs);
            Db.closeStatement(st);
        }
    }
    
    private static void inserirAtributosBasicosMovimentacao (Movimentacao movimentacao, PreparedStatement st) throws SQLException{
        st.setString(1, movimentacao.getDescricao());
        st.setDate(2, new java.sql.Date(movimentacao.getDataMov().getTime()));
        st.setInt(3, movimentacao.getTipo().ordinal());
    }
    
    private static Movimentacao instanciarMovimentacao(ResultSet rs){
        try{
            Movimentacao mov = new Movimentacao();
                mov.setId(rs.getInt("id"));
                mov.setDescricao(rs.getString("desc_mov"));
                mov.setDataMov(rs.getDate("data_mov"));
                mov.setTipo(TipoMovimentacao.recuperarTipoMovimentacao(rs.getInt("tipo_mov")));
                return mov;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    
    
}
