package models.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import models.enums.StatusVenda;
import models.exceptions.VendaException;

public class Venda {
    
    private Integer id;
    
    private Double totalVenda;
    
    private String cliente;
    
    private List<ItemVenda> itensVenda;
    
    private Date dataVenda;
    
    private StatusVenda statusVenda;

    public Venda() {
        itensVenda = new ArrayList<>();
        statusVenda = StatusVenda.NAOINICIADA;
    }

    public Venda(Integer id, Double totalVenda, String cliente, List<ItemVenda> itensVenda, Date dataVenda) {
        itensVenda = new ArrayList<>();
        this.id = id;
        this.totalVenda = totalVenda;
        this.cliente = cliente;
        this.itensVenda = itensVenda;
        this.dataVenda = dataVenda;
    }
    
    public StatusVenda iniciarVenda(){
        if(statusVenda.equals(StatusVenda.CANCELADA) || statusVenda.equals(StatusVenda.FINALIZADA)){
            throw new VendaException("Nao e possivel iniciar uma venda ja finalizada");
        }
        if(statusVenda.equals(StatusVenda.INICIADA) || statusVenda.equals(StatusVenda.EMANDAMENTO)){
            throw new VendaException("Precisa concluir a venda atual antes de iniciar uma nova venda");
        }
        statusVenda = StatusVenda.INICIADA;
        return statusVenda;
    }
    
    public void adicionarItemNaVenda(ItemVenda itemVenda){
        // verificacao se ha uma venda e se esta em progresso
        // adicao do valor do item de venda ao valor total
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(Double totalVenda) {
        this.totalVenda = totalVenda;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public StatusVenda getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(StatusVenda statusVenda) {
        this.statusVenda = statusVenda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", totalVenda=" + totalVenda + ", cliente=" + cliente + ", itensVenda=" + itensVenda + ", dataVenda=" + dataVenda + ", statusVenda=" + statusVenda + '}';
    }
    
}
