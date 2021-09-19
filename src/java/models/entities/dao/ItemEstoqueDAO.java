/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities.dao;

import java.util.List;
import models.entities.ItemEstoque;

/**
 *
 * @author bruno
 */
public interface ItemEstoqueDAO {
            
    void inserir(ItemEstoque itemEstoque);
    
    void atualizar(ItemEstoque itemEstoque);
    
    void deletarPeloId(int id);
    
    ItemEstoque buscarPeloId(int id);
    
    List<ItemEstoque> listarEstoque();
    
    boolean verificarItemEstoque(String codBarras);
    
    List<ItemEstoque> buscarTodosItemEstoqueCodBarras(String filtro);
    
    List<ItemEstoque> buscarTodosItemEstoqueNome(String nome);
    
    List<ItemEstoque> buscarTodosItemEstoqueCategoria(String categoria);
    
}
