package service;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
public class EmailService {
	
		public void sentEmail(String emailID,String Date,String messagepara,String subjectpara){
			new MailClient().mail(emailID,Date,messagepara,subjectpara);
		}
		final class MailClient
		{
			private class SMTPAuthenticator extends Authenticator
			{
				private PasswordAuthentication authentication;

				public SMTPAuthenticator(String login, String password)
				{
					authentication = new PasswordAuthentication(login, password);
				}

				@Override
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return authentication;
				}
			}
			public void mail(String toEmailId, String date,String msg,String sub)
			{
				try
				{
					
					String pwd = "Divya1991";
					//email id
					String maild = "sindhuri.devarakonda@gmail.com";

					

					Properties props = new Properties();
					props.setProperty("mail.host", "smtp.gmail.com");
					props.setProperty("mail.smtp.port", "587");
					props.setProperty("mail.smtp.auth", "true");
					props.setProperty("mail.smtp.starttls.enable", "true");

					Authenticator auth = new SMTPAuthenticator(maild, pwd);

					Session session = Session.getInstance(props, auth);

					MimeMessage message = new MimeMessage(session);

					try
					{
						message.setText(msg);
						message.setSubject(sub);
						message.setFrom(new InternetAddress(maild));
						message.addRecipient(Message.RecipientType.TO,
								new InternetAddress(toEmailId));
						Transport.send(message);
					}
					catch (MessagingException ex)
					{
						
					}
				}catch(Exception e){
					System.out.println(e);
				}
			}

}
}
