///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package entity;
//
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
///**
// *
// * @author baova
// */
//public class SendMail {
//    public static  void send(String to, String sub,
//            String msg, final String user, final String pass) {
//
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(user, pass);
//            }
//        });
//
//        try {
//
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(user));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject(sub);
//            message.setContent(msg, "text/html; charset=UTF-8");
//            
//            Transport.send(message);
//
//        } catch (MessagingException e) {
//        }
//    }
//    
//    public static void main(String[] args) {
//        String subject = "Reset code";
//                String message = "<!DOCTYPE html>\n"
//                        + "<html lang=\"en\">\n"
//                        + "\n"
//                        + "<head>\n"
//                        + "</head>\n"
//                        + "\n"
//                        + "<body>\n"
//                        + "    <h3 style=\"color: blue;\">Do not share this code with anyone.</h3>\n"
//                        + "    <div>Your OTP code <strong>" + 123465 + "</strong></div>\n"
//                        + "    <h3 style=\"color: blue;\">Thank you!</h3>\n"
//                        + "\n"
//                        + "</body>\n"
//                        + "\n"
//                        + "</html>";
//        SendMail.send("thoiban913@gmail.com", subject, message, "manhphhe150568@fpt.edu.vn", "16Phm.@&");
//    }
//}
