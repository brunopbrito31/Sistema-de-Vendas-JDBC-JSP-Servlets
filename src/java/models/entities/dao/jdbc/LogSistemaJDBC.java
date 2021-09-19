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
import models.entities.LogSistema;
import models.entities.dao.LogSistemaDAO;

public class LogSistemaJDBC implements LogSistemaDAO {
    
    private Connection conn;

    public LogSistemaJDBC(Connection conn) {
        this.conn = conn;
    }
    
    private final String queryInsercaoLogSistema =  "INSERT INTO tb_eventos " +
                                                    "(nome_evento, desc_evento, data_evento, orig_evento) " +
                                                    "VALUES " +
                                                    "(?, ?, ?, ?)";
    
    
    private final String erroInsercao = "Houve um erro de insercao.";
    
    private final String queryAtualizacaoLog = "UPDATE tb_eventos " +
                                               "SET nome_evento = ?, desc_evento = ?, data_evento = ?, orig_evento = ? " +
                                               "WHERE id = ?";
    
    private final String queryExclusaoLog = "DELETE FROM tb_estoques WHERE id = ?";
    
    private final String queryBuscarLog = "SELECT tb_eventos.*, "
                                        + "tb_eventos.nome_evento, "
                                        + "tb_eventos.desc_evento, "
                                        + "tb_eventos.data_evento, "
                                        + "tb_eventos.orig_evento, "
                                        + "FROM tb_eventos " 
                                        + "WHERE 1=1";
    
    private final String queryBuscarLogPeloId = queryBuscarLog.concat(" AND tb_eventos.id = ?");

    @Override
    public void inserir(LogSistema logSistema) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                queryInsercaoLogSistema,
                Statement.RETURN_GENERATED_KEYS);
            
            inserirAtributosBasicosLog(logSistema,st);

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    Integer id = rs.getInt(1);
                    logSistema.setId(id);
                }
                Db.closeResultSet(rs);
            } else {
                throw new DbException(erroInsercao);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeStatement(st);
        }
    }
    
    @Override
    public void atualizar(LogSistema logSistema) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(queryAtualizacaoLog);
            inserirAtributosBasicosLog(logSistema,st);
            st.setDouble(5, logSistema.getId());
            
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
            st = conn.prepareStatement(queryExclusaoLog);

            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        } 
    }
    
    @Override
    public LogSistema buscarPeloId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(queryBuscarLogPeloId);
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                LogSistema logSistema = instanciarLogSistema(rs);
                return logSistema;
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
    public List<LogSistema> buscarTodosOsLogs() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(queryBuscarLog);
            rs = st.executeQuery();
            List<LogSistema> logsSistema = new ArrayList<>();

            while (rs.next()) {
                LogSistema logSistemaAtual = instanciarLogSistema(rs);
                logsSistema.add(logSistemaAtual);
            }
            return logsSistema;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeResultSet(rs);
            Db.closeStatement(st);
        }
    }
    
    private static LogSistema instanciarLogSistema(ResultSet rs){
        try{
            LogSistema log = new LogSistema();
                log.setId(rs.getInt("id"));
                log.setNomeEvento(rs.getString("nome_evento"));
                log.setDescricaoEvento(rs.getString("desc_evento"));
                log.setMomentoEvento(rs.getTimestamp("data_evento"));
                log.setOrigem(rs.getString("orig_evento"));
                
                return log;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    
    private static void inserirAtributosBasicosLog (LogSistema logSistema, PreparedStatement st) throws SQLException{
        st.setString(1, logSistema.getNomeEvento());
        st.setString(2, logSistema.getDescricaoEvento());
        st.setDate(3, new java.sql.Date(logSistema.getMomentoEvento().getTime()));
        st.setString(4, logSistema.getOrigem());
    }
}
