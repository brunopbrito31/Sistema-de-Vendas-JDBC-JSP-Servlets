/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author bruno
 */
public class GeradorEmail {
    
    public static void enviarEmail(String emailPEnvio, String conteudo, String assunto, String emailOrigem, String senhaOrigem, String dest1, String dest2){
        
        Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication()
           {
                 return new PasswordAuthentication(emailOrigem,senhaOrigem);
           }
        });
        
            /** Ativa Debug para sessão */
        session.setDebug(true);
    
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailOrigem));
        //Remetente

            Address[] toUser = InternetAddress 
                     .parse(dest1+", "+dest2+", "
                             +emailPEnvio );

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);
            message.setText(conteudo);
        /**Método para enviar a mensagem criada*/
            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
       
    
}
