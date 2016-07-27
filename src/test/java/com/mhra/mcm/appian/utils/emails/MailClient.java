package com.mhra.mcm.appian.utils.emails;

import java.io.IOException;
import static java.lang.System.out;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailClient extends Authenticator {

    public static final int SHOW_MESSAGES = 1;
    public static final int CLEAR_MESSAGES = 2;
    public static final int SHOW_AND_CLEAR =
            SHOW_MESSAGES + CLEAR_MESSAGES;
    protected String from;
    protected Session session;
    protected PasswordAuthentication authentication;

    public static void main(String [] args){
        MailClient mc = new MailClient();

    }

    public MailClient() {
        String user = "mhra.uat";
        String host = "gmail.com";
        boolean debug = false;
        from = user + '@' + host;
        authentication = new PasswordAuthentication(user, user);
        Properties props = new Properties();
        props.put("mail.user", user);
        props.put("mail.host", host);
        props.put("mail.debug", debug ? "true" : "false");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        session = Session.getDefaultInstance(props);
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return authentication;
    }

    public void sendMessage(Message post) throws MessagingException, IOException {
        Message message = new MimeMessage(session);
        InternetAddress address = new InternetAddress("thufir@dur");
        message.setRecipient(Message.RecipientType.TO, address);
        message.setSubject(post.getSubject());
        Multipart mp = new MimeMultipart();
        BodyPart part = new MimeBodyPart();
        part.setContent(post.getContent(), "text/html");
        mp.addBodyPart(part);
        message.setContent(mp);
        Transport.send(message);
    }

    public void checkInbox(int mode)
            throws MessagingException, IOException {
        if (mode == 0) {
            return;
        }
        boolean show = (mode & SHOW_MESSAGES) > 0;
        boolean clear = (mode & CLEAR_MESSAGES) > 0;
        String action =
                (show ? "Show" : "")
                        + (show && clear ? " and " : "")
                        + (clear ? "Clear" : "");
        out.println(action + " INBOX for " + from);
        Store store = session.getStore();
        store.connect();
        out.println(store.getDefaultFolder());
        Folder root = store.getDefaultFolder();
        Folder inbox = root.getFolder("inbox");
        inbox.open(Folder.READ_WRITE);
        Message[] msgs = inbox.getMessages();
        if (msgs.length == 0 && show) {
            System.out.println("No messages in inbox");
        }
        for (int i = 0; i < msgs.length; i++) {
            MimeMessage msg = (MimeMessage) msgs[i];
            if (show) {
                System.out.println("    From: " + msg.getFrom()[0]);
                System.out.println(" Subject: " + msg.getSubject());
                System.out.println(" Content: " + msg.getContent());
            }
            if (clear) {
                msg.setFlag(Flags.Flag.DELETED, true);
            }
        }
        inbox.close(true);
        store.close();
        System.out.println();
    }
}