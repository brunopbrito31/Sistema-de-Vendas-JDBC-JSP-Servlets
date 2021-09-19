/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities.dao;

import java.util.List;
import models.entities.Categoria;

/**
 *
 * @author bruno
 */
public interface CategoriaDAO {
    
    void inserir(Categoria categoria);

    void atualizar(Categoria categoria);

    void deletarPeloId(Integer id);

    Categoria buscarPeloId(Integer id);

    List<Categoria> buscarTodasAsCategorias();
    
}
