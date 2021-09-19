/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities.dao;

import java.util.List;
import models.entities.Cliente;

/**
 *
 * @author bruno
 */
public interface ClienteDAO {
    
    void inserir(Cliente cliente);
    
    void atualizar(Cliente cliente);
    
    void deletarPeloId(Integer id);
    
    Cliente buscarPeloId(Integer id);
    
    List<Cliente> buscarTodosOsClientes();
    
}
