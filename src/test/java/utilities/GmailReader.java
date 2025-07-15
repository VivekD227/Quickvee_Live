package utilities;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

public class GmailReader {

    public static String getResetLinkFromEmail(String userEmail, String password) {
        try {
            System.out.println("Starting Gmail session...");

            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", userEmail, password);
            System.out.println("Connected to Gmail");

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            System.out.println("Inbox opened");

            // Get last 20 unread emails
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            //System.out.println("Unread messages found: " + messages.length);

            // Read latest first
            for (int i = messages.length - 1; i >= 0; i--) {
                Message message = messages[i];
                String subject = message.getSubject();
               // System.out.println("Checking email: " + subject);

                if (subject != null && subject.toLowerCase().contains("reset")) {
                    System.out.println("Found reset password email");

                    String content = getTextFromMessage(message);
                   // System.out.println("Email content:\n" + content);

                    // Match link
                    Pattern pattern = Pattern.compile("href=[\"'](https?://quickvee\\.com/changePassword[^\"']+)[\"']");
                    Matcher matcher = pattern.matcher(content);

                    if (matcher.find()) {
                        String resetLink = matcher.group(1);
                      //  System.out.println("✅ Reset link found: " + resetLink);
                        inbox.close(false);
                        store.close();
                        return resetLink;
                    } else {
                        System.out.println("⚠️ Reset link not found in content.");
                    }
                }
            }

            inbox.close(false);
            store.close();

        } catch (Exception e) {
            System.out.println("❌ Exception in GmailReader: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private static String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("text/html")) {
            return (String) message.getContent();
        } else if (message.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) message.getContent();
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart part = multipart.getBodyPart(i);
                if (part.isMimeType("text/html")) {
                    return (String) part.getContent();
                }
            }
        }
        return "";
    }
    
    public static class EmailContent {
        public String subject;
        public String body;

        public EmailContent(String subject, String body) {
            this.subject = subject;
            this.body = body;
        }
    }

    public static EmailContent getLatestResetEmail(String userEmail, String password) {
        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", userEmail, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (int i = messages.length - 1; i >= 0; i--) {
                Message message = messages[i];
                String subject = message.getSubject();

                if (subject != null && subject.toLowerCase().contains("reset password")) {
                    String body = getTextFromMessage(message);
                    inbox.close(false);
                    store.close();
                    return new EmailContent(subject, body);
                }
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String getTextFromEmail(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) message.getContent();
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart part = multipart.getBodyPart(i);
                if (part.isMimeType("text/plain")) {
                    return part.getContent().toString();
                } else if (part.isMimeType("text/html")) {
                    String html = (String) part.getContent();
                    return html.replaceAll("\\<.*?\\>", ""); // strip HTML tags
                }
            }
        }
        return "";
    }

}