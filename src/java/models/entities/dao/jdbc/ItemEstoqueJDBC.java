package models.entities.dao.jdbc;

import db.Db;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.entities.Categoria;
import models.entities.ItemEstoque;
import models.entities.Produto;
import models.entities.dao.ItemEstoqueDAO;
import models.enums.StatusCadastro;
import models.enums.UnidadeMedida;

/**
 *
 * @author bruno
 */
public class ItemEstoqueJDBC implements ItemEstoqueDAO{
    
    private Connection conn;
    
    private final String queryInsercaoEstoque = "INSERT INTO tb_estoques " +
                                                "(id_produto, quantidade, status) " +
                                                "VALUES " +
                                                "(?, ?, ?)";
    
    private final String erroInsercaoEstoque = "Ocorreu um erro. Não houveram linhas afetadas!";
    
    private final String queryAtualizacaoEstoque = "UPDATE tb_estoques " +
                                                   "SET id_produto = ?, quantidade = ?, status = ? " +
                                                   "WHERE id = ?";
    
    private final String queryExclusaoEstoque = "DELETE FROM tb_estoques WHERE id = ?";
    
    private final String queryBuscarEstoque = "SELECT tb_estoques.*, "
                                            + "tb_produtos.nome, "
                                            + "tb_produtos.cod_barras, "
                                            + "tb_produtos.valor_custo, "
                                            + "tb_produtos.valor_venda, "
                                            + "tb_produtos.img, "
                                            + "tb_produtos.id_categoria, "
                                            + "tb_produtos.unidade_medida, "
                                            + "tb_produtos.status_cadastro, "
                                            + "tb_categorias.nome AS categ FROM tb_estoques " 
                                            + "INNER JOIN tb_produtos "
                                            + "ON tb_estoques.id_produto = tb_produtos.id "
                                            + "INNER JOIN tb_categorias "
                                            + "ON tb_produtos.id_categoria = tb_categorias.id "
                                            + "WHERE 1=1";
    
    private final String queryBuscarEstoquePeloId = queryBuscarEstoque.concat(" AND tb_estoques.id = ?");
    
    private final String queryBuscarEstoquePeloCodProduto = queryBuscarEstoque.concat(" AND tb_produtos.cod_barras = ?");
    
    private final String filtroBuscarEstoquePeloCodProduto = " AND tb_produtos.cod_barras = ?";
    
    private final String filtroBuscarEstoquePeloNome = " AND tb_produtos.nome LIKE ?";
    
    private final String filtroBuscarEstoquePelaCategoria = " AND tb_produtos.id_categoria = ?";
    

    public ItemEstoqueJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(ItemEstoque itemEstoque) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                queryInsercaoEstoque,
                Statement.RETURN_GENERATED_KEYS);
            
            inserirAtributosBasicosItemEstoque(itemEstoque,st);

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    Integer id = rs.getInt(1);
                    itemEstoque.setId(id);
                }
                Db.closeResultSet(rs);
            } else {
                throw new DbException(erroInsercaoEstoque);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public void atualizar(ItemEstoque itemEstoque) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(queryAtualizacaoEstoque);
            inserirAtributosBasicosItemEstoque(itemEstoque,st);
            st.setDouble(4, itemEstoque.getId());
            
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
            st = conn.prepareStatement(queryExclusaoEstoque);

            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
        }
    }

    @Override
    public ItemEstoque buscarPeloId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(queryBuscarEstoquePeloId);
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                ItemEstoque itemEstoque = instanciarItemEstoque(rs);
                return itemEstoque;
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
    public List<ItemEstoque> listarEstoque() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(queryBuscarEstoque);
            rs = st.executeQuery();
            List<ItemEstoque> itensEstoque = new ArrayList<>();

            while (rs.next()) {
                ItemEstoque itemEstoqueAtual = instanciarItemEstoque(rs);
                itensEstoque.add(itemEstoqueAtual);
            }
            return itensEstoque;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Db.closeResultSet(rs);
            Db.closeStatement(st);
        }
    }

    @Override
    public boolean verificarItemEstoque(String codBarras) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(queryBuscarEstoquePeloCodProduto);
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
    
    @Override // verificar a integridade desse metodo e propagar
    public List<ItemEstoque> buscarTodosItemEstoqueCodBarras(String codBarras){
        return buscarTodosItemEstoqueCFiltro(filtroBuscarEstoquePeloCodProduto,codBarras);
    } 
    
    @Override
    public List<ItemEstoque> buscarTodosItemEstoqueNome(String nome){
        if(nome.equals("")){
            return listarEstoque();
        }else{
            return buscarTodosItemEstoqueCFiltro(filtroBuscarEstoquePeloNome,nome);
        }
    }
    
    
    @Override
    public List<ItemEstoque> buscarTodosItemEstoqueCategoria(String categoria){
        return buscarTodosItemEstoqueCFiltro(filtroBuscarEstoquePelaCategoria,categoria);
    } 
    
    private List<ItemEstoque> buscarTodosItemEstoqueCFiltro(String filtro, String conteudoBusca) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(queryBuscarEstoque.concat(filtro));
            if(filtro.equals(filtroBuscarEstoquePeloNome)){
                st.setString(1, "%"+conteudoBusca+"%");
            }else{
                if(filtro.equals(filtroBuscarEstoquePelaCategoria)){
                    st.setInt(1, Integer.parseInt(conteudoBusca));
                }else{
                    st.setString(1, conteudoBusca);
                }
            }
            rs = st.executeQuery();
            List<ItemEstoque> itens = new ArrayList<>();
            if(rs.next()){
                ItemEstoque itemEstoqueAt = instanciarItemEstoque(rs);
                itens.add(itemEstoqueAt);
            }
            return itens;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Db.closeStatement(st);
            Db.closeResultSet(rs);
        }
    }
    
    private static ItemEstoque instanciarItemEstoque(ResultSet rs){
        try{
            Categoria cat = new Categoria();
                cat.setId(rs.getInt("id_categoria"));
                cat.setNome(rs.getString("categ"));
                
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setCodBarras(rs.getString("cod_barras"));
                produto.setValorCusto(rs.getDouble("valor_custo"));
                produto.setValorVenda(rs.getDouble("valor_venda"));
                produto.setImagem(rs.getString("img"));
                produto.setCategoria(cat);
                produto.setUnidadeMedida(UnidadeMedida.recuperarUnidade(rs.getInt("unidade_medida")));
                produto.setStatusCadastro(StatusCadastro.recuperarStatus(rs.getInt("status_cadastro")));

                ItemEstoque itemEstoque = new ItemEstoque();
                itemEstoque.setId(rs.getInt("id"));
                itemEstoque.setProduto(produto);
                itemEstoque.setQuantidade(rs.getDouble("quantidade"));
                itemEstoque.setStatus(StatusCadastro.recuperarStatus(rs.getInt("status")));
                
                return itemEstoque;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    
    private static void inserirAtributosBasicosItemEstoque (ItemEstoque itemEstoque, PreparedStatement st) throws SQLException{
        st.setInt(1, itemEstoque.getProduto().getId());
        st.setDouble(2, itemEstoque.getQuantidade());
        st.setInt(3, itemEstoque.getStatus().ordinal());
    }
    
}
