package com.example.licenta.services;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSenderService extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "EmailSender";
    private static final String SMTP_USERNAME = "popovicilaura19@gmail.com";
    private static final String SMTP_PASSWORD = "jqmfjaxiabbujixg";
    private static final String EMAIL_FROM = "creditloanapp@digitalbank.com";
    private static final String EMAIL_TO = "laurica.popovici@gmail.com";

    private String subject;
    private String body;

    public MailSenderService(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(EMAIL_TO));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            Log.i(TAG, "Email sent successfully");
        } catch (MessagingException e) {
            Log.e(TAG, "Error sending email: " + e.getMessage());
        }

        return null;
    }
}


