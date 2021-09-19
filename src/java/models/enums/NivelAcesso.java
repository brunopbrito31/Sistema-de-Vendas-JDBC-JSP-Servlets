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
public enum NivelAcesso {
    
    VENDEDOR(0),
    ESTOQUISTA(1),
    GERENTE(2);
    
    int nivel;
    
    NivelAcesso(int p){
        nivel = p;
    }
    
    int mostrarNivel(){
        return nivel;
    }
    
    
    
    public static NivelAcesso recuperarNivel(int valorNivel){
        NivelAcesso nivelAcesso = NivelAcesso.GERENTE;
        switch(valorNivel){
                case 0:
                    nivelAcesso = NivelAcesso.VENDEDOR;
                    break;
                case 1:
                    nivelAcesso = NivelAcesso.ESTOQUISTA;
                    break;
                case 2:
                    nivelAcesso = NivelAcesso.GERENTE;
                    break;
            }
        return nivelAcesso;
    }
}
