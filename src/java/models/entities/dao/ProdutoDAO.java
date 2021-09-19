package models.entities.dao;

import java.util.List;
import models.entities.Produto;

public interface ProdutoDAO {
        
    void inserir(Produto produto);
    
    void atualizar(Produto produto);
    
    void deletarPeloId(int id);
    
    Produto buscarPeloId(int id);
    
    List<Produto> buscarTodosOsProdutos();
    
    boolean verificarProduto(String codBarras);
    
    List<Produto> buscarTodosOsProdutosCFiltro(String nome, String codB, String cat, String status);
    
}
