/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

import java.util.Objects;
import models.enums.StatusCadastro;

/**
 *
 * @author bruno
 */
public class ItemEstoque {
    
    private Integer id;
    
    private Produto produto;
    
    private Double quantidade;
    
    private StatusCadastro status;

    public ItemEstoque() {
    }

    public ItemEstoque(Integer id, Produto produto, Double quantidade, StatusCadastro statusCadastro) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.status = statusCadastro;
    }

    public StatusCadastro getStatus() {
        return status;
    }

    public void setStatus(StatusCadastro status) {
        this.status = status;
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

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final ItemEstoque other = (ItemEstoque) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemEstoque{" + "id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", status=" + status + '}';
    }
}
