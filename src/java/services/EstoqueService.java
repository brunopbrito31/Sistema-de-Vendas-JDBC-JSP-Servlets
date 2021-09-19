/*
 * Classe com a finalidade de realizar os tratamentos necessarios na manipualacao do estoque 
 */
package services;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import models.entities.ItemEstoque;
import models.entities.Movimentacao;
import models.entities.Produto;
import models.entities.dao.FabricaDAO;
import models.entities.dao.ItemEstoqueDAO;
import models.enums.StatusCadastro;
import models.enums.TipoMovimentacao;

/**
 *
 * @author bruno
 */
public class EstoqueService {
    
    private final ItemEstoqueDAO itemEstoqueDAO;
    //private final List<ItemEstoque> itensDoEstoque;
    
    // Lista de item de estoque ate agora nao esta sendo utilizada, retirei pois nao vejo necessidade em se caregar todos os itens em memoria
    public EstoqueService(){
        this.itemEstoqueDAO = FabricaDAO.criarItemEstoqueDAO();
        //this.itensDoEstoque = itemEstoqueDAO.listarEstoque();
    }
    
    //private String filtro;
    
    public void adicionarItemNoEstoque(Produto produto, Double quantidade, TipoMovimentacao tipoMovimentacao){
        if(quantidade <=0) throw new IllegalArgumentException("Quantidade invalida");
        String codBarrasAtual = produto.getCodBarras();
        Boolean itemTemEstoque = itemEstoqueDAO.verificarItemEstoque(codBarrasAtual);
        if(itemTemEstoque){
            ItemEstoque itemEstoqueExistente = itemEstoqueDAO.buscarTodosItemEstoqueCodBarras(codBarrasAtual).get(0);
            Double quantidadeAntiga = itemEstoqueExistente.getQuantidade();
            itemEstoqueExistente.setQuantidade(quantidadeAntiga+quantidade);
            if(itemEstoqueExistente.getStatus().equals(StatusCadastro.INATIVO)){
                itemEstoqueExistente.setStatus(StatusCadastro.ATIVO);
            }
            itemEstoqueDAO.atualizar(itemEstoqueExistente);
        }else{
            ItemEstoque itemEstoqueNovo = new ItemEstoque();
            itemEstoqueNovo.setProduto(produto);
            itemEstoqueNovo.setQuantidade(quantidade);
            itemEstoqueNovo.setStatus(StatusCadastro.ATIVO);
            itemEstoqueDAO.inserir(itemEstoqueNovo);
        }
        registrarMovimentacaoEstoque(produto,quantidade,tipoMovimentacao);
    }
    
    public void removerItemDoEstoque(Produto produto, Double quantidade, TipoMovimentacao tipoMovimentacao) {
        if(quantidade <=0) throw new IllegalArgumentException("Quantidade invalida");
        String codBarrasAtual = produto.getCodBarras();
        Boolean itemTemEstoque = itemEstoqueDAO.verificarItemEstoque(codBarrasAtual);
        if(itemTemEstoque){
            ItemEstoque itemEstoqueExistente = itemEstoqueDAO.buscarTodosItemEstoqueCodBarras(codBarrasAtual).get(0);
            if(itemEstoqueExistente.getQuantidade() < quantidade) {
                throw new IllegalArgumentException("Nao ha estoque suficiente: "+itemEstoqueExistente.getQuantidade());
            }
            Double quantidadeAntiga = itemEstoqueExistente.getQuantidade();
            itemEstoqueExistente.setQuantidade(quantidade - quantidadeAntiga);
            if(itemEstoqueExistente.getQuantidade().equals(0)){
                itemEstoqueExistente.setStatus(StatusCadastro.INATIVO);
            }
            itemEstoqueDAO.atualizar(itemEstoqueExistente);
        }else{
            throw new IllegalArgumentException("Nao ha estoque do produto");
        }
        registrarMovimentacaoEstoque(produto,quantidade,tipoMovimentacao);
    }
    
    // Implementar os métodos com filtro
    public List<ItemEstoque> listarEstoque(){
        return itemEstoqueDAO.listarEstoque();
    }
    
    public List<ItemEstoque> listarEstoqueCompleto(String tipo, String conteudo){
        if(tipo.equals("codBarras")){
            return itemEstoqueDAO.buscarTodosItemEstoqueCodBarras(conteudo);
        }else if(tipo.equals("nome")){
            return itemEstoqueDAO.buscarTodosItemEstoqueNome(conteudo);
        }else if(tipo.equals("categoria")){
            return itemEstoqueDAO.buscarTodosItemEstoqueCategoria(conteudo);
        }else{
            return itemEstoqueDAO.listarEstoque();
        }
    }
    
    private static void registrarMovimentacaoEstoque(Produto produto, Double quantidade, TipoMovimentacao tipoMovimentacao){
        Movimentacao movimentacaoAtual = new Movimentacao();
        movimentacaoAtual.setDescricao("Id do Produto: "+produto.getId()+" Quantidade: "+quantidade);
        movimentacaoAtual.setDataMov(Date.from(Instant.now()));
        movimentacaoAtual.setTipo(tipoMovimentacao);
        FabricaDAO.criarMovimentacaoDAO().inserir(movimentacaoAtual);
    }
    
}
