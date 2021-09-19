/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author bruno
 */

//Nome da tabela no banco: tb_eventos, implementar a camada de sql dentro do software
public class LogSistema {
    
    private Integer id;
    
    private String nomeEvento;
    
    private String descricaoEvento;
    
    private Date momentoEvento;
    
    private String origem;

    public LogSistema(Integer id, String nomeEvento, String descricaoEvento, Date momentoEvento, String origem) {
        this.id = id;
        this.nomeEvento = nomeEvento;
        this.descricaoEvento = descricaoEvento;
        this.momentoEvento = momentoEvento;
        this.origem = origem;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public LogSistema() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    public Date getMomentoEvento() {
        return momentoEvento;
    }

    public void setMomentoEvento(Date momentoEvento) {
        this.momentoEvento = momentoEvento;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final LogSistema other = (LogSistema) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LogSistema{" + "id=" + id + ", nomeEvento=" + nomeEvento + ", descricaoEvento=" + descricaoEvento + ", momentoEvento=" + momentoEvento + ", origem=" + origem + '}';
    }
}
