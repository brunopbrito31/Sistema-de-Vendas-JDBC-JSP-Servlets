/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities.dao;

import java.util.List;
import models.entities.LogSistema;

/**
 *
 * @author bruno
 */
public interface LogSistemaDAO {
        
    void inserir(LogSistema logSistema);
    
    void atualizar(LogSistema logSistema);
    
    void deletarPeloId(Integer id);
    
    LogSistema buscarPeloId(Integer id);
    
    List<LogSistema> buscarTodosOsLogs();
}
