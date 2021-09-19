/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.entities.ItemVenda;
import models.entities.Venda;

public class VendaService {
    
    Venda vendaAtual;
    
    EstoqueService estoqueService;
    
    public VendaService(){
        this.estoqueService = new EstoqueService();
        this.vendaAtual = new Venda();
    }
    
    public void iniciarVenda(){
        
    }
    
    public void adicionarItemNaVenda(ItemVenda itemVenda){
        
    }
    
}
