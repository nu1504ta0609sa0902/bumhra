package com.mhra.mcm.appian.utils.emails;

/**
 * Created by TPD_Auto on 27/07/2016.
 */

import com.mhra.mcm.appian.domain.sort.SortByMessageNumber;
import com.mhra.mcm.appian.domain.sub.Invoice;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;

public class EmailUtils {

    private static List<Invoice> listOfInvoices = new ArrayList<>();

    public static void main(String[] args) {

        String host = "pop.gmail.com";// change accordingly
        String mailStoreType = "pop3";
        String username = "mhra.uat@gmail.com";// change accordingly
        String password = "MHRA1234";// change accordingly

        //Call method fetch
        fetch(host, mailStoreType, username, password);

    }


    public static List<Invoice> getListOfInvoicesFromGmail(Properties emailDetails) {
        //
        String host = emailDetails.getProperty("test.email.host");
        String mailStoreType = emailDetails.getProperty("test.email.mailStoreType");
        String username = emailDetails.getProperty("test.email.username");
        String password = emailDetails.getProperty("test.email.password");

        //Call method fetch
        fetch(host, mailStoreType, username, password);

        //
        return listOfInvoices;
    }

    public static void fetch(String pop3Host, String storeType, final String user,
                             final String password) {
        try {
            // create properties field
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imap");
            properties.put("mail.pop3.host", pop3Host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");

            //Session emailSession = Session.getDefaultInstance(properties);
            Session emailSession = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });

            // create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(pop3Host, user, password);

            // create the folder object and open it
            Folder emailFolder = store.getFolder("Inbox");
            emailFolder.open(Folder.READ_ONLY);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    System.in));

            // retrieve the messages from the folder in an array and print it
            int count = emailFolder.getMessageCount();
//            int fromCount = count - 10;
//            if(fromCount < 0){
//                fromCount = 1;
//            }
//            Message[] messages = emailFolder.getMessages(fromCount, count);
            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            List<Message> lom = Arrays.asList(messages);
            Collections.sort(lom, new SortByMessageNumber());
            messages = lom.toArray( new Message[lom.size()] );
            System.out.println("Number of messages : " + messages.length);


            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                Date sentDate = message.getSentDate();
                String subject = message.getSubject();
                System.out.println(subject);
                Address[] froms = message.getFrom();

                for (Address from : froms) {
                    String emailAddress = froms == null ? null : ((InternetAddress) from).getAddress();
                    if (emailAddress != null && emailAddress.contains("appian")) {

                        boolean isMessageReceivedToday = isMessageReceivedToday(subject);
                        if (isMessageReceivedToday) {
                            boolean isRecent = receivedInLast(5, sentDate);
                            if(isRecent){
                                System.out.println("Recent email received");
                                System.out.println("---------------------------------");
                                getInvoicesFromCSV(message);
                                System.out.println("Number of invoices : " + listOfInvoices.size());
                            }
                        } else {
                            System.out.println("Message is old or not relevant" );
                        }
                    }
                }

            }

            // close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * We are only interested in todays email
     * @param subject
     * @return
     */
    private static boolean isMessageReceivedToday(String subject) {
        Calendar calendar = Calendar.getInstance();
        int dom = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        String f1 = dom + "/" + month + "/" + year;
        String f2 = dom + "/0" + month + "/" + year;

        if (subject.contains(f1) || subject.contains(f2))
            return true;
        else
            return false;
    }

    /**
     * Check if email was received in the last X minuit
     * @param i
     * @param receivedDate
     * @return
     */
    private static boolean receivedInLast(int i, Date receivedDate) {
        long receivedTime = receivedDate.getTime();
        long currentTime = new Date().getTime();
        int time = i * 60 * 1000;

        if (receivedTime > (currentTime - time))
            return true;
        else
            return false;
    }

    /*
    * This method checks for content-type
    * based on which, it processes and
    * fetches the content of the message
    */
    public static void getInvoicesFromCSV(Part p) throws Exception {
        if (p instanceof Message)
            //Call methos writeEnvelope
            writeEnvelope((Message) p);

        System.out.println("----------------------------");
        System.out.println("CONTENT-TYPE: " + p.getContentType());

        //check if the content is plain text
        if (p.isMimeType("text/plain")) {
            System.out.println("This is plain text");
            System.out.println("---------------------------");
            System.out.println((String) p.getContent());
        }
        //check if the content has attachment
        else if (p.isMimeType("multipart/*")) {
            System.out.println("This is a Multipart");
            System.out.println("---------------------------");
            Multipart mp = (Multipart) p.getContent();
            int count = mp.getCount();
            for (int i = 0; i < count; i++)
                getInvoicesFromCSV(mp.getBodyPart(i));
        }
        //check if the content is a nested message
        else if (p.isMimeType("message/rfc822")) {
            System.out.println("This is a Nested Message");
            System.out.println("---------------------------");
            getInvoicesFromCSV((Part) p.getContent());
        }
        //check if the content is an inline image
//        else if (p.isMimeType("image/jpeg")) {
//            System.out.println("--------> image/jpeg");
//            Object o = p.getContent();
//
//            InputStream x = (InputStream) o;
//            // Construct the required byte array
//            System.out.println("x.length = " + x.available());
//            while ((i = (int) ((InputStream) x).available()) > 0) {
//                int result = (int) (((InputStream) x).read(bArray));
//                if (result == -1)
//                    int i = 0;
//                byte[] bArray = new byte[x.available()];
//
//                break;
//            }
//            FileOutputStream f2 = new FileOutputStream("/tmp/image.jpg");
//            f2.write(bArray);
//        }
        else if (p.getContentType().contains("image/")) {
            System.out.println("content type" + p.getContentType());
            File f = new File("image" + new Date().getTime() + ".jpg");
            DataOutputStream output = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(f)));
            com.sun.mail.util.BASE64DecoderStream test =
                    (com.sun.mail.util.BASE64DecoderStream) p
                            .getContent();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = test.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } else {
            Object o = p.getContent();
            if (o instanceof String) {
                System.out.println("This is a string");
                System.out.println("---------------------------");
                System.out.println((String) o);
            } else if (o instanceof InputStream) {
                System.out.println("This is just an input stream");
                System.out.println("---------------------------");
                InputStream is = (InputStream) o;
                is = (InputStream) o;

                BufferedReader br = null;
                StringBuilder sb = new StringBuilder();
                String line;
                try {
                    int lineNumber = 0;
                    br = new BufferedReader(new InputStreamReader(is));
                    while ((line = br.readLine()) != null) {
                        //Ignore first line
                        if(lineNumber > 0){
                            sb.append(line+"\n");
                            Invoice invoice = new Invoice(line);
                            if(!listOfInvoices.contains(invoice))
                                listOfInvoices.add(invoice);
                        }
                        lineNumber++;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                System.out.println(sb.toString());
//                int c;
//                while ((c = is.read()) != -1) {
//                    System.out.write(c);
//                }
            } else {
                System.out.println("This is an unknown type");
                System.out.println("---------------------------");
                System.out.println(o.toString());
            }
        }

    }

    /*
    * This method would print FROM,TO and SUBJECT of the message
    */
    public static void writeEnvelope(Message m) throws Exception {
        System.out.println("This is the message envelope");
        System.out.println("---------------------------");
        Address[] a;

        // FROM
        if ((a = m.getFrom()) != null) {
            for (int j = 0; j < a.length; j++)
                System.out.println("FROM: " + a[j].toString());
        }

        // TO
        if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
            for (int j = 0; j < a.length; j++)
                System.out.println("TO: " + a[j].toString());
        }

        // SUBJECT
        if (m.getSubject() != null)
            System.out.println("SUBJECT: " + m.getSubject());

    }

}