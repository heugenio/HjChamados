
package br.com.hjsystems.hjchamados.util;

import static java.lang.System.out;
import javax.swing.JTextArea;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;


/**
 *
 * @author Daniel
 */
public class EnviaEmail {
    
    public static void sendEmail(String emailDest, String nomeDest, String assunto, String msg) {
        
        try {
            SimpleEmail email = new SimpleEmail();
            //Utilize o hostname do seu provedor de email
            System.out.println("alterando hostname...");
            //email.setDebug(true);
            email.setHostName("mail.hjsystems.com.br");
            //Quando a porta utilizada não é a padrão (gmail = 465)
            email.setSmtpPort(465);
            //Adicione os destinatários
            email.addTo(emailDest, nomeDest);
            //Configure o seu email do qual enviará
            email.setFrom("informativo@hjsystems.com.br", "Informativo Financeiro");
            //Adicione um assunto
            email.setSubject(assunto);
            //Adicione a mensagem do email
            email.setMsg(msg);
            //Para autenticar no servidor é necessário chamar os dois métodos abaixo
            System.out.println("autenticando...");
            email.setSSL(true);
            email.setTLS(true);
            email.setAuthentication("informativo@hjsystems.com.br", "hj140267");
            System.out.println("enviando...");
            email.send();
            System.out.println("Email enviado!");
        }catch(EmailException e) {
            throw new RuntimeException(e);
        }
    }
    

    public static void sendEmailHtml(String emailDest, String nomeDest, String assunto, String msg, JTextArea jTaLog) throws EmailException {

        HtmlEmail email = new HtmlEmail();
        email.setSSLOnConnect(true);
        email.setHostName("mail.hjsystems.com.br");
        email.setSslSmtpPort("465");
        email.setSSL(true);
        email.setTLS(true);
        email.setAuthentication("informativo@hjsystems.com.br", "hj140267");
        //System.out.println("autenticando...");
        jTaLog.append("autenticando... \n");
        jTaLog.requestFocus();
        jTaLog.setCaretPosition(jTaLog.getText().length());
        try {
            email.setFrom("informativo@hjsystems.com.br", "Informativo Financeiro");
            email.setDebug(false);
            email.setSubject(assunto);

            StringBuilder builder = new StringBuilder();
            builder.append(msg);

            email.setHtmlMsg(builder.toString());
            email.addTo(emailDest, nomeDest);
            //System.out.println("enviando...");
            jTaLog.append("enviando para " + emailDest + "... \n");
            jTaLog.requestFocus();
            jTaLog.setCaretPosition(jTaLog.getText().length());
            email.send();

        } catch (EmailException e) {
            e.printStackTrace();
        } finally {
            //System.out.println("Email enviado!");
            jTaLog.append("Email enviado! \n");
            jTaLog.requestFocus();
            jTaLog.setCaretPosition(jTaLog.getText().length());
        }
    }

    public static void sendEmailAnexo(String emailDest, String nomeDest, String assunto, String msg, String caminhoArquivo) throws EmailException {

        HtmlEmail email = new HtmlEmail();
        email.setSSLOnConnect(true);
        email.setHostName("mail.hjsystems.com.br");
        email.setSslSmtpPort("465");
        email.setSSL(true);
        email.setTLS(true);
        email.setAuthentication("informativo@hjsystems.com.br", "hj140267");
        //System.out.println("autenticando...");
        try {
            email.setFrom("informativo@hjsystems.com.br", "Informativo de Vendas - HJ Systems");
            email.setDebug(false);
            email.setSubject(assunto);

            EmailAttachment anexo = new EmailAttachment();
            anexo.setPath(caminhoArquivo);
            anexo.setDisposition(EmailAttachment.ATTACHMENT);
            anexo.setName("Informativo_Diario.pdf");
            email.attach(anexo);

            StringBuilder builder = new StringBuilder();
            builder.append("<body>\n"
                    + "<table width=\"100%\" border=\"0\">\n"
                    + "  <tr>\n"
                    + "    <td width=\"14%\"><img src=\"http://hjsystems.com.br/wp-content/uploads/2015/02/logo-sit2.png\" width=\"225\" height=\"51\" /></td>\n"
                    + "    <td width=\"86%\"><p>Olá "+nomeDest+", o seu informativo diario de vendas esta disponível em anexo.</p>\n"
                    + "    <p style=\"font-size:10px;\"> Caso não queira mais continuar recebendo este email, entre em contato com um de nossos consultores.</p></td>\n"
                    + "  </tr>\n"
                    + "</table>\n"
                    + "</body>");
            email.setHtmlMsg(builder.toString());
            email.addTo(emailDest, nomeDest);
            //System.out.println("enviando...");
            email.send();

        } catch (EmailException e) {
            e.printStackTrace();
        } finally {
            out.println("Email enviado!");
        }
    }
}
