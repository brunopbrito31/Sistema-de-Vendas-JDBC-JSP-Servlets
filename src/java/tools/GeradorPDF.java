/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ElementTags;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import static java.time.Instant.now;
import java.util.List;
import models.entities.Cliente;
import models.entities.Venda;

/**
 *
 * @author bruno
 * colocar imagens e personalizar o pdf
 */
public class GeradorPDF {
    
    public static void gerarPdfVendas(List<Venda> vendas, String autor){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("relatorio_vendas.pdf"));
            document.open();

            Font font = FontFactory.getFont(FontFactory.TIMES, 16, Font.NORMAL);
            
            Font fontTitle = FontFactory.getFont(FontFactory.COURIER_BOLD, 20, Font.BOLD);

            Paragraph p1 = new Paragraph("Relatório de Vendas" , fontTitle   );
            p1.setSpacingAfter(5);
            p1.setFirstLineIndent(30);
            p1.setAlignment(ElementTags.ALIGN_CENTER);
            document.add(p1); 
            
            Image im1 = new Image(Image.getInstance("src/vendas.jpg")) {
                @Override
                public void setUrl(URL url) {
                    super.setUrl(url);
                }
            };

            im1.scaleAbsoluteHeight(350);
            im1.scaleAbsoluteWidth(500);
            im1.setAlignment(Element.ALIGN_CENTER);
            
            document.add(im1);
            
            for(Venda x: vendas){
                Paragraph p = new Paragraph("Numero da transação: "+x.getId()+" Valor da transação R$:"+x.getTotalVenda()+" Cliente: "+x.getCliente() , font   );
                p.setSpacingAfter(5);
                p.setFirstLineIndent(30);
                p.setAlignment(ElementTags.ALIGN_LEFT);
                document.add(p);
            }
                  
            Paragraph p2 = new Paragraph("Nome do autor: "+autor , fontTitle   );
            p2.setSpacingAfter(5);
            p2.setFirstLineIndent(30);
            p2.setAlignment(ElementTags.ALIGN_RIGHT);
            document.add(p2);
            
            document.close();
        } catch (IOException | DocumentException e) {
            System.out.println("houve erro: "+e.getMessage());
        }
    }
    
    public static void gerarPdfClientes (List<Cliente> clientes, String autor){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("rel_clientes_cadas.pdf"));
            document.open();

            Font font = FontFactory.getFont(FontFactory.TIMES, 16, Font.NORMAL);
            Font font2 = FontFactory.getFont(FontFactory.TIMES, 16, Font.UNDERLINE);
            
            Font fontTitle = FontFactory.getFont(FontFactory.COURIER_BOLD, 20, Font.BOLD);

            Paragraph p1 = new Paragraph("Relatório de Clientes" , fontTitle   );
            p1.setSpacingAfter(5);
            p1.setFirstLineIndent(30);
            p1.setAlignment(ElementTags.ALIGN_CENTER);
            document.add(p1); 
            
            Image im1 = new Image(Image.getInstance("vendas.jpg")) {
                @Override
                public void setUrl(URL url) {
                    super.setUrl(url);
                }
            };

            im1.scaleAbsoluteHeight(350);
            im1.scaleAbsoluteWidth(500);
            im1.setAlignment(Element.ALIGN_CENTER);
            
            document.add(im1);
            
            for(Cliente x: clientes){
                Paragraph p = new Paragraph("Id: "+x.getId()+" Nome:"+x.getNome()+" Idade: "+x.getIdade()+" Peso: "+x.getPeso(), font2   );
                p.setSpacingAfter(5);
                p.setFirstLineIndent(30);
                p.setAlignment(ElementTags.ALIGN_LEFT);
                document.add(p);
            }
                  
            Paragraph p2 = new Paragraph("Nome do autor: "+autor , fontTitle   );
            p2.setSpacingAfter(5);
            p2.setFirstLineIndent(30);
            p2.setAlignment(ElementTags.ALIGN_RIGHT);
            document.add(p2);
            
            
            //Data não funcionou, estudar como adicionar
//            Paragraph p3 = new Paragraph(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Instant.now()) , fontTitle   );
//            p2.setSpacingAfter(5);
//            p2.setFirstLineIndent(30);
//            p2.setAlignment(ElementTags.ALIGN_RIGHT);
//            document.add(p3);
            
            document.close();
        } catch (IOException | DocumentException e) {
            System.out.println("houve erro: "+e.getMessage());
        }
    }
    
}
