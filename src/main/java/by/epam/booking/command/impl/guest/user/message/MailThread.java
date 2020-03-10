package by.epam.booking.command.impl.guest.user.message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailThread extends Thread {

    private static Logger logger = LogManager.getLogger();
    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private String login;
    private final String MESSAGE_TEMPLATE = "Message from: ";
    private final String CHARSET = "utf-8";

    public MailThread(String sendToEmail,
                      String mailSubject, String mailText, String login) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.login = login;
    }
    private void init() {

        Session mailSession = (new SessionCreator()).createSession();
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        try {
            message.setSubject(mailSubject);
            //message.setContent(mailText, "text/html");
            mailText =  MESSAGE_TEMPLATE + login + '\n'+ mailText;
            message.setText(mailText,CHARSET);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
        } catch (MessagingException e) {
         logger.error(e);
        }
    }
    public void run() {
        init();
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error(e);
        }
    }
}