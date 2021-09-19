/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import models.entities.Produto;

/**
 *
 * @author bruno
 */
public class Auxiliar {
    
    //Trabalho com datas
    
    private static final String[] diasDaSemana = {"DOM", "SEG", "TER", "QUA", "QUI", "SEX", "SAB"};
    private static final String[] mesesDoAno = {"jan", "fev", "mar", "abr", "mai", "jun", "jul", "ago", "set", "out", "nov", "dez"};
    private static final  Map<String, String> mensagensErro = new HashMap(){
        {
            put("permissao","Sem permissão, consulte o administrador");
            put("operacao","Operação não permitida");
            put("conflitoCodBarras","Código de Barras já cadastrado na base de dados");
            put("erroDeGravacaoBanco","Erro ao tentar gravar as informações");
        }
    };
        
    public static String getMensagensErro(String key) {
        if(mensagensErro.containsKey(key)){
            return mensagensErro.get(key);
        }else{
            return "Erro Inesperado";
        }
    }

    public static String retornarDiaDaSemana(Date data){
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        return diasDaSemana[cal.get(Calendar.DAY_OF_WEEK)-1];        
    }
    
    public static String retornarMesDoAno(Date data){
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        return mesesDoAno[cal.get(Calendar.MONTH)];
    }
    
    public static String retornarDataPadrao(Date data){
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        StringBuilder sb = new StringBuilder();
        String diaSemana = retornarDiaDaSemana(data);
        String mesDoAno = retornarMesDoAno(data);
        sb.append(diaSemana).append(", ").
                append(cal.get(Calendar.DAY_OF_MONTH)).
                append(" de ").append(mesDoAno).append(" ").append("*").append(" ").append(cal.get(Calendar.HOUR)).append(":").append(cal.get(Calendar.MINUTE));
        return sb.toString();
    }
    
    
    //Trabalho com paginação
    
    //
    public static int rertornarQtdPaginas(int qtElementosTotal){
        int qtPaginas;
        if(qtElementosTotal%10 != 0){
            qtPaginas = ((qtElementosTotal/10)+1);
        }else{
            qtPaginas = qtElementosTotal/10;
        }
        return qtPaginas;
    }

    public static List<Produto> gerarListaPaginada (List<Produto> listaAlvo , int pagAtual, int qtElementosPag){
        final int paginaAtual = pagAtual;
        final int qtElementosPorPagina = qtElementosPag;
        List<Produto> listaASerRenderizada = listaAlvo;
        
        System.out.println("Testando o valor da lista a ser renderizada (dentro do método de geração)"+listaASerRenderizada.toString());
        
        return listaASerRenderizada.stream()
                .filter(x -> listaASerRenderizada.indexOf(x) >= (qtElementosPorPagina * paginaAtual) && listaASerRenderizada.indexOf(x)<((qtElementosPorPagina * paginaAtual)+qtElementosPag))
                .collect(Collectors.toList());
    }
}
 