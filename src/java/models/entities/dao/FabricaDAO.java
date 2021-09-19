/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities.dao;

import db.Db;
import models.entities.dao.jdbc.CategoriaJDBC;
import models.entities.dao.jdbc.ClienteJDBC;
import models.entities.dao.jdbc.ItemEstoqueJDBC;
import models.entities.dao.jdbc.MovimentacaoJDBC;
import models.entities.dao.jdbc.ProdutoJDBC;
import models.entities.dao.jdbc.UsuarioJDBC;
import models.entities.dao.jdbc.VendaJDBC;

/**
 *
 * @author bruno
 */
public class FabricaDAO {
    
    public static ClienteDAO criarClienteDAO() {
        return new ClienteJDBC(Db.getConnection());
    }
    
    public static VendaDAO criarVendaDAO() {
        return new VendaJDBC(Db.getConnection());
    }
    
    public static UsuarioDAO criarUsuarioDAO() {
        return new UsuarioJDBC(Db.getConnection());
    }
    
    public static ProdutoDAO criarProdutoDAO() {
        return new ProdutoJDBC(Db.getConnection());
    }
    
    public static CategoriaDAO criarCategoriaDAO() {
        return new CategoriaJDBC(Db.getConnection());
    }
    
    public static ItemEstoqueDAO criarItemEstoqueDAO() {
        return new ItemEstoqueJDBC(Db.getConnection());
    }
    
    public static MovimentacaoDAO criarMovimentacaoDAO() {
        return new MovimentacaoJDBC(Db.getConnection());
    }
}
