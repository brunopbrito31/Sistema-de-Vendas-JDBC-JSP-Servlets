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
public enum TipoMovimentacao {
        
    VENDA(0),
    BAIXAINTERNA(1),
    ESTORNO(2),
    ENTRADA(3);
    
    private int tipo;
    
    TipoMovimentacao(int tM){
        tipo = tM;
    }
    
    int mostrarTipo(){
        return tipo;
    }
    
    public static TipoMovimentacao recuperarTipoMovimentacao(int tipoMovimentacao){
        
        TipoMovimentacao tipoMovimentacaoRetorno = TipoMovimentacao.BAIXAINTERNA;
        
        switch(tipoMovimentacao){
                case 0:
                    tipoMovimentacaoRetorno = TipoMovimentacao.VENDA;
                    break;
                case 1:
                    tipoMovimentacaoRetorno = TipoMovimentacao.BAIXAINTERNA;
                    break;
                case 2:
                    tipoMovimentacaoRetorno = TipoMovimentacao.ESTORNO;
                    break;
                case 3:
                    tipoMovimentacaoRetorno = TipoMovimentacao.ENTRADA;
                    break;
            }
        return tipoMovimentacaoRetorno;
    }
    
}
