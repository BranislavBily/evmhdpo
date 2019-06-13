package sample.email.emailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    private final static String sender = "evmhdpo@gmail.com";
    private final static String senderPassword = "W#C#!Gy@eMN8feq&s7HF";
    private final static String passwordChangedSubject = "Your password was changed!";



    private static Properties prop = new Properties();


    public static void sendVerificationEmail(String receiver, String subject, int code) {
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, senderPassword);
                    }
                });
        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(receiver)
            );
            message.setSubject(subject);
            String messageText = "<div>\n" +
                    "    <p>Your verification code is: </p>\n" +
                    "    <h1 style=\"color:brown\">"+code+"</h1>\n" +
                    "</div>\n" +
                    "<br>\n" +
                    "<br>\n" +
                    "<br>\n" +
                    "<br>\n" +
                    "<br>\n" +
                    "<br>\n" +
                    "<p>If you did not request changing password please contant our administrator at: wittner@spse-po.sk</p>";
            message.setContent(messageText, "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("email was send");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendPasswordChangedEmail(String receiver) {
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, senderPassword);
                    }
                });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(receiver)
            );
            message.setSubject(passwordChangedSubject);
            message.setContent(createPasswordChangedContent(), "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("email was send");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendEmail(String receiver, String subject, String content) {
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, senderPassword);
                    }
                });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(receiver)
            );
            message.setSubject(subject);
            message.setContent(content, "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("email was send");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static String createPasswordChangedContent() {
        return "<div>\n" +
                "    <p>Your password has been successfully changed!</p>\n" +
                "    <br><br><br><br>\n" +
                "    <p>If your did not change your password please contact our administrator at wittner@spse-po.sk</p>\n" +
                "</div>";
    }

}
