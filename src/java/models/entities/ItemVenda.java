package models.entities;

import java.util.Objects;

public class ItemVenda {
    
    private Integer id;
    
    private Produto produto;
    
    private Integer quantidade;
    
    private Double valorTotal;
    
    private Venda vendaOrigem;

    public ItemVenda(Integer id, Produto produto, Integer quantidade, Double valorTotal, Venda vendaOrigem) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.vendaOrigem = vendaOrigem;
    }

    public ItemVenda() {
        this.valorTotal = 0d;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        this.valorTotal = this.quantidade * this.getProduto().getValorVenda();
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Venda getVendaOrigem() {
        return vendaOrigem;
    }

    public void setVendaOrigem(Venda vendaOrigem) {
        this.vendaOrigem = vendaOrigem;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final ItemVenda other = (ItemVenda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemVenda{" + "id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", valorTotal=" + valorTotal + ", vendaOrigem=" + vendaOrigem + '}';
    }
}
