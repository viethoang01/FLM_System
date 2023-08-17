//package Verifycation;
//
//import entity.User;
//import java.util.Properties;
//import java.util.Random;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//
//
//public class SendEmail {
//    //generate vrification code
//public static void main(String[] args) {
//    System.out.println(getRandom());
//    User user = new User();
//    user.setEmail("hoangnvliu@gmail.com");
//        boolean a = sendEmail(user, "Verifycation code", "verifi code: "+getRandom());
//    }
//    public static String getRandom() {
//        Random rnd = new Random();
//        int number = rnd.nextInt(999999);
//        return String.format("%06d", number);
//    }
//    
//    public static void send(String to, String sub,
//            String msg, final String user, final String pass) {
//        //create an instance of Properties Class   
//        Properties props = new Properties();
//
//        /* Specifies the IP address of your default mail server
//     	   for e.g if you are using gmail server as an email sever
//           you will pass smtp.gmail.com as value of mail.smtp host. 
//           As shown here in the code. 
//           Change accordingly, if your email id is not a gmail id
//         */
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        //below mentioned mail.smtp.port is optional
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//
//        /* Pass Properties object(props) and Authenticator object   
//           for authentication to Session instance 
//         */
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(user, pass);
//            }
//        });
//
//        try {
//
//            /* Create an instance of MimeMessage, 
// 	      it accept MIME types and headers 
//             */
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(user));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject(sub);
//            message.setContent(msg, "text/html");
//
//            /* Transport class is used to deliver the message to the recipients */
//            Transport.send(message);
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    //send email to the user email
//    public static boolean  sendEmail(User user, String mailsubject, String message) {
//        boolean test = false;
//
//        String toEmail = user.getEmail();
//        String fromEmail = "datlthe151019@fpt.edu.vn";
//        String password = "thuylinh2000";
//
//        try {
//
//            // your host email smtp server details
//            Properties pr = new Properties();
//            pr.setProperty("mail.smtp.host", "smtp.gmail.com");
//            pr.setProperty("mail.smtp.port", "587");
//            pr.setProperty("mail.smtp.auth", "true");
//            pr.setProperty("mail.smtp.starttls.enable", "true");
//            pr.put("mail.smtp.socketFactory.port", "587");
//            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//            
//            //get session to authenticate the host email address and password
//            Session session = Session.getInstance(pr, new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(fromEmail, password);
//                }
//            });
//
//            //set email message details
//            Message mess = new MimeMessage(session);
//
//            //set from email address
//            mess.setFrom(new InternetAddress(fromEmail));
//            //set to email address or destination email address
//            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
//
//            //set email subject
//            mess.setSubject(mailsubject);
//
//            //set message text
//            mess.setText(message);
//
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText("utf-8", "html");
//
//            //send the message
//            Transport.send(mess);
//
//            test = true;
//
//        } catch (MessagingException e) {
//        }
//
//        return test;
//    }
//}
