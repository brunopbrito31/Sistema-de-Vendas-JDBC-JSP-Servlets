/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities.dao.jdbc;

import db.Db;
import db.DbException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.Cliente;
import models.entities.ItemVenda;
import models.entities.Produto;
import models.entities.Venda;
import models.entities.dao.VendaDAO;
/**
 *
 * @author bruno
 */
public class VendaJDBC implements VendaDAO {
    
    private Connection conn;

    public VendaJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Venda venda) {
        PreparedStatement st = null;
	try {
            st = conn.prepareStatement(
            "INSERT INTO tb_vendas "
            + "(valor_total, cliente, data_venda) "
            + "VALUES "
            + "(?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS);
		
            st.setDouble(1, venda.getTotalVenda());
            st.setString(2, venda.getCliente());
            st.setDate(3, new java.sql.Date( venda.getDataVenda().getTime()));
			
            int rowsAffected = st.executeUpdate();
			
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    venda.setId(id);
                }
		Db.closeResultSet(rs);
            }
            else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
       	}
    }

    @Override
    public void atualizar(Venda venda) {
        PreparedStatement st = null;
	try {
            st = conn.prepareStatement(
            "UPDATE tb_vendas "
            + "SET valor_total = ?, cliente = ?, data_venda = ? "
            + "WHERE id = ?");
		
            st.setDouble(1, venda.getTotalVenda());
            st.setString(2, venda.getCliente());
            st.setDate(3, new java.sql.Date(venda.getDataVenda().getTime()));
		
            st.executeUpdate();
	}catch (SQLException e) {
            throw new DbException(e.getMessage());
	}finally {
            Db.closeStatement(st);
	}
    }

    @Override
    public void deletarPeloId(int id) {
        PreparedStatement st = null;
	try {
            st = conn.prepareStatement("DELETE FROM tb_vendas WHERE Id = ?");
		
            st.setInt(1, id);
			
            st.executeUpdate();
	}catch (SQLException e) {
            throw new DbException(e.getMessage());
	}finally {
            Db.closeStatement(st);
	}
    }

    @Override
    public Venda buscarPeloId(int id) {
        PreparedStatement st = null;
	ResultSet rs = null;
	try {
            st = conn.prepareStatement(
                "SELECT tb_vendas.*, tb_item_venda.id AS iditemvenda, tb_item_venda.quantidade, tb_item_venda.valor_total, tb_produtos.id as idproduto, tb_produtos.nome, tb_produtos.cod_barras, tb_produtos.valor_custo, tb_produtos.valor_venda, tb_produtos.img "+ 
                "FROM tb_vendas "+ 
                "LEFT JOIN tb_item_venda "+
                "ON tb_vendas.id = tb_item_venda.venda_id "+ 
                "INNER JOIN tb_produtos "+
                "ON tb_item_venda.produto_id = tb_produtos.id "+ 
                "WHERE tb_vendas.id = ?");
			
            st.setInt(1, id);
            
            Venda venda = new Venda();
            venda.setId(null);

            rs = st.executeQuery();
            if (rs.next()) { // A venda tem 2 itens de venda e o método só está puxando 1
                if(venda.getId() == null){
                    venda.setId(rs.getInt("id"));
                    venda.setCliente("cliente");
                    venda.setItensVenda(new ArrayList<>());
                    venda.setTotalVenda(rs.getDouble("valor_total"));
                    venda.setDataVenda(rs.getDate("data_venda"));
                }
                
                Produto produto = new Produto();
                produto.setId(rs.getInt("idproduto"));
                produto.setNome(rs.getString("nome"));
                produto.setCodBarras(rs.getString("cod_barras"));
                produto.setValorCusto(rs.getDouble("valor_custo"));
                produto.setValorVenda(rs.getDouble("valor_venda"));
                produto.setImagem(rs.getString("img"));
                
                ItemVenda itemVenda = new ItemVenda();
                itemVenda.setId(rs.getInt("iditemvenda"));
                itemVenda.setQuantidade(rs.getInt("quantidade"));
                itemVenda.setValorTotal(rs.getDouble("tb_item_venda.valor_total"));
                itemVenda.setVendaOrigem(venda);
                itemVenda.setProduto(produto);
                
                venda.getItensVenda().add(itemVenda);
            }if(venda.getId() != null){
                return venda;
            }
            return null;
	}catch (SQLException e) {
            throw new DbException(e.getMessage());
	}
	finally {
            Db.closeStatement(st);
            Db.closeResultSet(rs);
	}
    }

    @Override
    public List<Venda> buscarTodasAsVendas() {
        return null;
//        PreparedStatement st = null;;;
//	ResultSet rs = null;
//	try {
//            st = conn.prepareStatement(
//            "SELECT tb_vendas.*,tb_clientes.nome, tb_clientes.idade, tb_clientes.peso "
//            + "FROM tb_vendas INNER JOIN tb_clientes "
//            + "ON tb_vendas.id_cliente = tb_clientes.id "
//            + "ORDER BY nome");
//			
//            rs = st.executeQuery();
//			
//            List<Venda> vendas = new ArrayList<>();
//            Map<Integer, Cliente> map = new HashMap<>();
//			
//            while (rs.next()) {
//				
//                Cliente cliente = map.get(rs.getInt("id_cliente"));
//				
//                if (cliente == null) {
//                    cliente = instanciarCliente(rs);
//                    map.put(rs.getInt("id_cliente"), cliente);
//		}
//				
//		Venda venda = instanciarVenda(rs, cliente);
//		vendas.add(venda);
//            }
//            return vendas;
//	}catch (SQLException e) {
//            throw new DbException(e.getMessage());
//	}finally {
//            Db.closeStatement(st);
//            Db.closeResultSet(rs);
//       	}
    }
    
    
//    private Venda instanciarVenda(ResultSet rs, Cliente cliente) throws SQLException {
//        Venda venda = new Venda();
//        venda.setId(rs.getInt("id"));
//	venda.setValor(rs.getString("valor"));
//        venda.setCliente(cliente);
//        return venda;
//    }

    private Cliente instanciarCliente(ResultSet rs) throws SQLException {
    	Cliente cliente = new Cliente();
    	cliente.setId(rs.getInt("id_cliente"));
	cliente.setNome(rs.getString("nome"));
        cliente.setIdade(rs.getString("idade"));
        cliente.setPeso(rs.getString("peso"));
	return cliente;
    }
   
    
}
