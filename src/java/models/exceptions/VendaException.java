/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.exceptions;

/**
 *
 * @author bruno
 */
public class VendaException extends IllegalArgumentException{
    
    public VendaException(String msg){
        super(msg);
    }
}
