/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author bruno
 */
public class ManipuladorArquivo {
    // Todo arquivo tem o caminho e nome

    /**
     *
     * @param pasta
     * @param nomeDoArquivo
     */
    public void upload(String pasta, String nomeDoArquivo, InputStream arquivoCarregado) throws FileNotFoundException{
        
        //Determina o novo caminho do aquivo
        String caminhoArquivo = pasta+"/"+nomeDoArquivo;
        
        //Copia os bites para um novo arquivo
        File novoArquivo = new File(caminhoArquivo);
        FileOutputStream saida = new FileOutputStream(novoArquivo);
        
        copiar(arquivoCarregado,saida);
        
    }
    
    private void copiar(InputStream origem, OutputStream destino) {
        int bite = 0;
        byte[] tamanhoMaximo = new byte[1024*8]; //8KB
        try{
            //Enquanto bytes forem sendo lidos
            while((bite = origem.read(tamanhoMaximo)) >= 0){
                //Pegue o byte lido e escreva no destino
                 destino.write(tamanhoMaximo,0,bite);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    
}
