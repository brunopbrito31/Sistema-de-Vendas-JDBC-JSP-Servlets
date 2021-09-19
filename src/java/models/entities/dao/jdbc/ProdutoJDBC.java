/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities.dao.jdbc;

import com.mysql.jdbc.Connection;
import db.Db;
import db.DbException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.entities.Categoria;
import models.entities.Produto;
import models.entities.dao.ProdutoDAO;
import models.enums.StatusCadastro;
import models.enums.UnidadeMedida;

/**
 *
 * @author bruno
 */
public class ProdutoJDBC implements ProdutoDAO{
    
    private Connection conn;

    public ProdutoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Produto produto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "INSERT INTO tb_produtos " +
                "(nome, cod_barras, valor_custo, valor_venda, img, id_categoria, unidade_medida, status_cadastro) " +
                "VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
            st.setString(1, produto.getNome());
            st.setString(2, produto.getCodBarras());
            st.setDouble(3, produto.getValorCusto());
            st.setDouble(4, produto.getValorVenda());
            st.setString(5, produto.getImagem());
            st.setInt(6, produto.getCategoria().getId());
            st.setInt(7, produto.getUnidadeMedida().ordinal());
            st.setInt(8, produto.getStatusCadastro().ordinal());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    Integer id = rs.getInt(1);
                    produto.setId(id);
                }
                Db.closeResultSet(rs);
            } else {
                throw new DbException("Ocorreu um erro. Não houveram linhas afetadas!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public void atualizar(Produto produto) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
              "UPDATE tb_produtos " +
                      "SET nome = ?, cod_barras = ?, valor_custo = ?, valor_venda = ?, img = ?, id_categoria = ?, unidade_medida = ?, status_cadastro = ? " +
                      "WHERE id = ?");
            st.setString(1, produto.getNome());
            st.setString(2, produto.getCodBarras());
            st.setDouble(3, produto.getValorCusto());
            st.setDouble(4, produto.getValorVenda());
            st.setString(5, produto.getImagem());
            st.setInt(6, produto.getCategoria().getId());
            st.setInt(7, produto.getUnidadeMedida().ordinal());
            st.setInt(8, produto.getStatusCadastro().ordinal());
            st.setInt(9, produto.getId());
            
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public void deletarPeloId(int id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM tb_produtos WHERE id = ?");

            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public Produto buscarPeloId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT tb_produtos.*,tb_categorias.nome AS categ FROM tb_produtos " +
                            "INNER JOIN tb_categorias "
                            + "ON tb_produtos.id_categoria = tb_categorias.id "+
                            "WHERE tb_produtos.id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Categoria cat = new Categoria();
                cat.setId(rs.getInt("id_categoria"));
                cat.setNome(rs.getString("categ"));
                return instanciarProduto(rs,cat);
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
    public List<Produto> buscarTodosOsProdutos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT tb_produtos.*,tb_categorias.nome AS categ FROM tb_produtos " +
                            "INNER JOIN tb_categorias "
                            + "ON tb_produtos.id_categoria = tb_categorias.id ");
            rs = st.executeQuery();
            List<Produto> produtos = new ArrayList<>();

            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId(rs.getInt("id_categoria"));
                cat.setNome(rs.getString("categ"));
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCodBarras(rs.getString("cod_barras"));
                produto.setValorCusto(rs.getDouble("valor_custo"));
                produto.setValorVenda(rs.getDouble("valor_venda"));
                produto.setImagem(rs.getString("img"));
                produto.setCategoria(cat);
                produto.setUnidadeMedida(UnidadeMedida.recuperarUnidade(rs.getInt("unidade_medida")));
                produto.setStatusCadastro(StatusCadastro.recuperarStatus(rs.getInt("status_cadastro")));
                produtos.add(produto);
            }
            return produtos;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeResultSet(rs);
            Db.closeStatement(st);
        }
    }
    
    @Override // Inserir mais um filtro, o de status
    public List<Produto> buscarTodosOsProdutosCFiltro(String nome, String codB, String cat, String status) {
        if(nome.isEmpty()) nome = "";
        if(codB.isEmpty()) codB = "";
        if(cat.isEmpty()) cat = "";
        PreparedStatement st = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT tb_produtos.*,tb_categorias.nome AS categ FROM tb_produtos " 
                            + "INNER JOIN tb_categorias "
                            + "ON tb_produtos.id_categoria = tb_categorias.id "
                            + "WHERE 1=1 "
               );
        Boolean complemento = !nome.equals("");
        if(complemento){
            sb.append("AND tb_produtos.nome like ? ");
        }
        Boolean complemento2 = !codB.equals("");
        if(complemento2){
            sb.append("AND tb_produtos.cod_barras = ? ");
        }
        Boolean complemento3 = !cat.equals("");
        if(complemento3){
            sb.append("AND tb_categorias.nome like ? ");
        }
        Boolean complemento4 = !status.equals("");
        if(complemento4){
            sb.append("AND tb_produtos.status_cadastro = ? ");
        }
        sb.append("ORDER BY tb_produtos.nome ");
        try {
            st = conn.prepareStatement(sb.toString());
            if(complemento){
                st.setString(1, "%"+nome+"%");
            }
            if(complemento2 && !complemento){
                st.setString(1, codB);
            }
            if(complemento2 && complemento){
                st.setString(2, codB);
            }
            if(complemento3){
                int n;
                if(complemento && complemento2){
                    n = 3;
                }else if (complemento || complemento2){
                    n = 2;
                }else{
                    n = 1;
                }
                System.out.println("valor de n: "+n);
                System.out.println("valor de cat: "+cat);
                st.setString(n, "%"+cat+"%");
            }
            if(complemento4){
                int t;
                if(complemento && complemento2 && complemento3){
                    t = 4;
                }else if ((complemento && complemento2) || (complemento && complemento3) || (complemento2 && complemento3)){
                    t = 3;
                }else if (complemento || complemento2 || complemento3){
                    t = 2;
                }else{
                    t = 1;
                }
                st.setInt(t, Integer.parseInt(status));
            }
            rs = st.executeQuery();
            List<Produto> produtos = new ArrayList<>();

            while (rs.next()) {
                Categoria cate = new Categoria();
                cate.setId(rs.getInt("tb_produtos.id_categoria"));
                cate.setNome(rs.getString("categ"));
                Produto produto = new Produto();
                produto.setId(rs.getInt("tb_produtos.id"));
                produto.setNome(rs.getString("tb_produtos.nome"));
                produto.setCodBarras(rs.getString("tb_produtos.cod_barras"));
                produto.setValorCusto(rs.getDouble("tb_produtos.valor_custo"));
                produto.setValorVenda(rs.getDouble("tb_produtos.valor_venda"));
                produto.setImagem(rs.getString("tb_produtos.img"));
                produto.setCategoria(cate);
                produto.setUnidadeMedida(UnidadeMedida.recuperarUnidade(rs.getInt("unidade_medida")));
                produto.setStatusCadastro(StatusCadastro.recuperarStatus(rs.getInt("status_cadastro")));
                produtos.add(produto);
            }
            return produtos;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeResultSet(rs);
            Db.closeStatement(st);
        }
    }

    @Override
    public boolean verificarProduto(String codBarras) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT * FROM tb_produtos " +
                            "WHERE tb_produtos.cod_barras = ?");
            st.setString(1, codBarras);
            rs = st.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
            Db.closeResultSet(rs);
        }
    }
    

    private Produto instanciarProduto(ResultSet rs, Categoria cat) {
        Produto produto = new Produto();
        try{
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setCodBarras(rs.getString("cod_barras"));
            produto.setValorCusto(rs.getDouble("valor_custo"));
            produto.setValorVenda(rs.getDouble("valor_venda"));
            produto.setImagem(rs.getString("img"));
            produto.setCategoria(cat);
            produto.setUnidadeMedida(UnidadeMedida.recuperarUnidade(rs.getInt("unidade_medida")));
            produto.setStatusCadastro(StatusCadastro.recuperarStatus(rs.getInt("status_cadastro")));
            return produto;
        }catch (SQLException e){
        throw new DbException("Error: "+e.getMessage());
        }
    }
    
}

