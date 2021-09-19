/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.enums;

public enum StatusVenda {
    
    INICIADA(0),
    EMANDAMENTO(1),
    FINALIZADA(2),
    CANCELADA(3),
    NAOINICIADA(4);
    
    private int status;
    
    StatusVenda(int p){
        status = p;
    }
    
    int mostrarStatus(){
        return status;
    }
    
    public static StatusVenda recuperarStatus(int statusVenda){
        
        StatusVenda statusDVenda = StatusVenda.CANCELADA;
        
        switch(statusVenda){
                case 0:
                    statusDVenda = StatusVenda.INICIADA;
                    break;
                case 1:
                    statusDVenda = StatusVenda.EMANDAMENTO;
                    break;
                case 2:
                    statusDVenda = StatusVenda.FINALIZADA;
                    break;
                case 3:
                    statusDVenda = StatusVenda.CANCELADA;
                    break;
                case 4:
                    statusDVenda = StatusVenda.NAOINICIADA;
                    break;
            }
        return statusDVenda;
    }
}
