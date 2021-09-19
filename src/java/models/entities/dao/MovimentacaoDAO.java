/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities.dao;

import java.util.List;
import models.entities.Movimentacao;
import models.enums.TipoMovimentacao;

/**
 *
 * @author bruno
 */
public interface MovimentacaoDAO {
    
    void inserir(Movimentacao movimentacao);
    
    void atualizar(Movimentacao movimentacao);
    
    void deletarPeloId(Integer id);
    
    Movimentacao buscarPeloId(Integer id);
    
    Movimentacao buscarPeloTipo(TipoMovimentacao tipo);
    
    List<Movimentacao> buscarTodasAsMovimentacoes();
}
