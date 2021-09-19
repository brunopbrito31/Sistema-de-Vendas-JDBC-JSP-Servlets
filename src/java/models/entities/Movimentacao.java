/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

import java.util.Date;
import java.util.Objects;
import models.enums.TipoMovimentacao;

/**
 *
 * @author bruno
 */
public class Movimentacao {
    
    private Integer id;
    
    private String descricao;
    
    private Date dataMov;
    
    private TipoMovimentacao tipo;

    public Movimentacao() {
    }
    
    public Movimentacao(Integer id, String descricao, Date dataMov, TipoMovimentacao tipo) {
        this.id = id;
        this.descricao = descricao;
        this.dataMov = dataMov;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataMov() {
        return dataMov;
    }

    public void setDataMov(Date dataMov) {
        this.dataMov = dataMov;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Movimentacao other = (Movimentacao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Movimentacao{" + "id=" + id + ", descricao=" + descricao + ", dataMov=" + dataMov + ", tipo=" + tipo + '}';
    }
    
}
