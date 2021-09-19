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
public enum UnidadeMedida {
    
    UNIDADE(0),
    KG(1);
    
    int unid;
    
    UnidadeMedida(int unidade){
        unid = unidade;
    }
    
    int mostrarUnidade(){
        return unid;
    }
    
    public static UnidadeMedida recuperarUnidade(int und){
        UnidadeMedida unidadeMedida = UnidadeMedida.UNIDADE;
        switch(und){
                case 0:
                    unidadeMedida = UnidadeMedida.UNIDADE;
                    break;
                case 1:
                    unidadeMedida = UnidadeMedida.KG;
                    break;
            }
        return unidadeMedida;
    }
}
