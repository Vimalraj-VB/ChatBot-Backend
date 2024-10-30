package com.example.chatbot.communication;

import java.util.Properties;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.sql.DataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;

@Service
public class OtpMailSendingImpl implements OtpMailSending {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${MAIL_HOSTNAME}")
	private String hostName;

	@Value("${MAIL_PORT}")
	private String portNo;

	@Value("${FROM_MAIL_USERNAME}")
	private String userName;

	@Value("${FROM_MAIL_PASSWORD}")
	private String password;

	@Value("${MAIL_INETADDRESS}")
	private String internetAddress;

	@Autowired
	Environment env;

	@Override
	public String sendOtp(String otp, String mail, String name) {
		String mailstatus = null;
		this.logger.info("=======>>> Enter  " + LoggerFactory.getLogger(getClass()) + " <<<=======");
		try {

			Properties properties = new Properties();

			properties.setProperty("mail.smtp.host", hostName);
			properties.setProperty("mail.smtp.port", portNo);
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.store.protocol", "pop3");
			properties.put("mail.transport.protocol", "smtp");
			properties.put("mail.debug.auth", "true");
			properties.put("mail.smtp.ssl.trust", hostName);
			try {
				Session session = Session.getDefaultInstance(properties, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(OtpMailSendingImpl.this.env.getProperty("FROM_MAIL_USERNAME"),
								OtpMailSendingImpl.this.env.getProperty("FROM_MAIL_PASSWORD"));
					}
				});

				MimeMessage message = new MimeMessage(session);
				message.setFrom((Address) new InternetAddress(internetAddress));
				message.setRecipients(Message.RecipientType.TO, (Address[]) InternetAddress.parse(mail));
				message.setSubject("One Time Password");

				StringBuilder htmlContent = new StringBuilder();

				htmlContent.append(
						"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
				htmlContent.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
				htmlContent.append("<head>");
				htmlContent.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
				htmlContent.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
				htmlContent.append("<title>Verify your login</title>");
				htmlContent.append(
						"<!--[if mso]><style type=\"text/css\">body, table, td, a { font-family: Arial, Helvetica, sans-serif !important; }</style><![endif]-->");
				htmlContent.append("</head>");
				htmlContent.append(
						"<body style=\"font-family: Helvetica, Arial, sans-serif; margin: 0px; padding: 0px; background-color: #ffffff;\">");
				htmlContent.append(
						"<table role=\"presentation\" style=\"width: 100%; border-collapse: collapse; border: 0px; border-spacing: 0px; font-family: Arial, Helvetica, sans-serif; background-color: rgb(239, 239, 239);\">");
				htmlContent.append("<tbody>");
				htmlContent.append("<tr>");
				htmlContent.append(
						"<td align=\"center\" style=\"padding: 1rem 2rem; vertical-align: top; width: 100%;\">");
				htmlContent.append(
						"<table role=\"presentation\" style=\"max-width: 600px; border-collapse: collapse; border: 0px; border-spacing: 0px; text-align: left;\">");
				htmlContent.append("<tbody>");
				htmlContent.append("<tr>");
				htmlContent.append("<td style=\"padding: 40px 0px 0px;\">");
				htmlContent.append("<div style=\"text-align: left;\">");
				htmlContent.append(
						"<div style=\"padding-bottom: 20px;\"><img src=\"https://i.ibb.co/Qbnj4mz/logo.png\" alt=\"Company\" style=\"width: 56px;\"></div>");
				htmlContent.append("</div>");
				htmlContent.append("<div style=\"padding: 20px; background-color: rgb(255, 255, 255);\">");
				htmlContent.append("<div style=\"color: rgb(0, 0, 0); text-align: left;\">");
				htmlContent.append("<h1 style=\"margin: 1rem 0\">Verification code</h1>");
				htmlContent.append(
						"<p style=\"padding-bottom: 16px\">Please use the verification code below to sign in.</p>");
				htmlContent.append(
						"<p style=\"padding-bottom: 16px\"><strong style=\"font-size: 130%\">" + otp + "</strong></p>");
				htmlContent.append(
						"<p style=\"padding-bottom: 16px\">If you didn’t request this, you can ignore this email.</p>");
				htmlContent.append("<p style=\"padding-bottom: 16px\">Thanks,<br>ChatBot Team</p>");
				htmlContent.append("</div>");
				htmlContent.append("</div>");
				htmlContent.append("<div style=\"padding-top: 20px; color: rgb(153, 153, 153); text-align: center;\">");
				htmlContent.append("<p style=\"padding-bottom: 16px\">Made with ♥ in ChatBot</p>");
				htmlContent.append("</div>");
				htmlContent.append("</td>");
				htmlContent.append("</tr>");
				htmlContent.append("</tbody>");
				htmlContent.append("</table>");
				htmlContent.append("</td>");
				htmlContent.append("</tr>");
				htmlContent.append("</tbody>");
				htmlContent.append("</table>");
				htmlContent.append("</body>");
				htmlContent.append("</html>");
				message.setFrom((Address) new InternetAddress(internetAddress));
				message.addRecipient(Message.RecipientType.TO, (Address) new InternetAddress(mail));
				message.setText(htmlContent.toString());
				MimeBodyPart textPart = new MimeBodyPart();
				textPart.setContent(htmlContent.toString(), "text/html; charset=UTF-8");
				MimeMultipart mimeMultipart = new MimeMultipart();
				mimeMultipart.addBodyPart((BodyPart) textPart);
				message.setContent((Multipart) mimeMultipart);
				Transport.send((Message) message);
				mailstatus = "Otp sended to your mail";
			} catch (Exception e) {
				e.printStackTrace();
				mailstatus = e.getMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.logger.info("=======>>> End  " + LoggerFactory.getLogger(getClass()) + " <<<=======");

		return mailstatus;
	}

}
