package by.epam.booking.command.impl.guest.user.message;

import by.epam.booking.config.ConfigurationManager;
import by.epam.booking.type.MessagePropertiesName;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionCreator {

    private String smtpHost;
    private String smtpPort;
    private String userName;
    private String userPassword;
    private Properties sessionProperties;

    public SessionCreator() {
        smtpHost = ConfigurationManager.getMailProperty(MessagePropertiesName.HOST_PROPERTY);
        smtpPort = ConfigurationManager.getMailProperty(MessagePropertiesName.PORT_PROPERTY);
        userName = ConfigurationManager.getMailProperty(MessagePropertiesName.NAME_PROPERTY);
        userPassword = ConfigurationManager.getMailProperty(MessagePropertiesName.PASSWORD_PROPERTY);
        System.out.println(userName);
        System.out.println(userPassword);

        sessionProperties = new Properties();
        sessionProperties.setProperty(MessagePropertiesName.TRANSPORT_PROTOCOL_PROPERTY,
                MessagePropertiesName.TRANSPORT_PROTOCOL_VALUE);
        sessionProperties.setProperty(MessagePropertiesName.MAIL_HOST_PROPERTY, smtpHost);
        sessionProperties.put(MessagePropertiesName.MAIL_AUTH_PROPERTY, MessagePropertiesName.MAIL_AUTH_VALUE);
        sessionProperties.put(MessagePropertiesName.PORT_PROPERTY, smtpPort);
        sessionProperties.put(MessagePropertiesName.MAIL_SOCKET_FACTORY_PORT_PROPERTY, smtpPort);
        sessionProperties.put(MessagePropertiesName.MAIL_SOCKET_FACTORY_CLASS_PROPERTY,
                MessagePropertiesName.MAIL_SOCKET_FACTORY_CLASS_VALUE);
        sessionProperties.put(MessagePropertiesName.MAIL_SOCKET_FACTORY_FALLBACK_PROPERTY,
                MessagePropertiesName.MAIL_SOCKET_FACTORY_FALLBACK_VALUE);
        sessionProperties.setProperty(MessagePropertiesName.MAIL_QUIT_WAIT_PROPERTY,
                MessagePropertiesName.MAIL_QUIT_WAIT_VALUE);
    }

    public Session createSession() {
        return Session.getDefaultInstance(sessionProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }
}