package models.entities;

import java.util.Objects;
import models.enums.StatusCadastro;
import models.enums.UnidadeMedida;

public class Produto {
    
    private Integer id;
    
    private String nome;
    
    private String codBarras;
    
    private Double valorCusto;
    
    private Double valorVenda;
    
    private String imagem;
    
    private String caminhoImagemCompleto = "produtos/imgs/"+imagem;
    
    private Categoria categoria;
    
    private StatusCadastro statusCadastro;
    
    private UnidadeMedida unidadeMedida;

    public Produto() {
    }

    public Produto(Integer id, String nome, String codBarras, Double valorCusto, Double valorVenda, String imagem, Categoria categoria, StatusCadastro statusCadastro, UnidadeMedida unidadeMedida) {
        this.id = id;
        this.nome = nome;
        this.codBarras = codBarras;
        this.valorCusto = valorCusto;
        this.valorVenda = valorVenda;
        this.imagem = imagem;
        this.categoria = categoria;
        this.statusCadastro = statusCadastro;
        this.unidadeMedida = unidadeMedida;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public Double getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(Double valorCusto) {
        this.valorCusto = valorCusto;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
        this.caminhoImagemCompleto = "produtos/imgs/"+imagem;
    }
    
    public String getImagem(){
        return imagem;
    }
    
    public String getCaminhoImagemCompleto() {
        return caminhoImagemCompleto;
    }

    public void setCaminhoImagemCompleto(String caminhoImagemCompleto) {
        this.caminhoImagemCompleto = caminhoImagemCompleto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public StatusCadastro getStatusCadastro() {
        return statusCadastro;
    }

    public void setStatusCadastro(StatusCadastro statusCadastro) {
        this.statusCadastro = statusCadastro;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.codBarras);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.codBarras, other.codBarras)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", codBarras=" + codBarras + ", valorCusto=" + valorCusto + ", valorVenda=" + valorVenda + ", imagem=" + imagem + ", caminhoImagemCompleto=" + caminhoImagemCompleto + ", categoria=" + categoria + ", statusCadastro=" + statusCadastro + ", unidadeMedida=" + unidadeMedida + '}';
    }
}
