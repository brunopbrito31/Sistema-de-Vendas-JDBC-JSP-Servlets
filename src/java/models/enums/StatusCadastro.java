/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.enums;

/**
 *
 * @author bruno
 */
public enum StatusCadastro {
        
    ATIVO(0),
    INATIVO(1);
    
    int status;
    
    StatusCadastro(int s){
        status = s;
    }
    
    int mostrarStatus(){
        return status;
    }
    
    
    
    public static StatusCadastro recuperarStatus(int sts){
        StatusCadastro statusCadastro = StatusCadastro.INATIVO;
        switch(sts){
                case 0:
                    statusCadastro = StatusCadastro.ATIVO;
                    break;
                case 1:
                    statusCadastro = StatusCadastro.INATIVO;
                    break;
            }
        return statusCadastro;
    }
}
