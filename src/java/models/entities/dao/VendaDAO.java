/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities.dao;

import java.util.List;
import models.entities.Venda;

/**
 *
 * @author bruno
 */
public interface VendaDAO {
    void inserir(Venda venda);
    
    void atualizar(Venda venda);
    
    void deletarPeloId(int id);
    
    Venda buscarPeloId(int id);
    
    List<Venda> buscarTodasAsVendas();
}
