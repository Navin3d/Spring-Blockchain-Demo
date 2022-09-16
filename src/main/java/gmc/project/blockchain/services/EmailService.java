package gmc.project.blockchain.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import gmc.project.blockchain.configurations.MailConfig;
import gmc.project.blockchain.models.MailingModel;

@Service
public class EmailService {
	
	@Autowired
	private MailConfig mailConfig;
	@Autowired
	private JavaMailSender mailSender;
	
	@Async
	public void sendMail(MailingModel mailingModel) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setSubject(mailingModel.getSubject());
			mimeMessageHelper.setFrom(new InternetAddress(mailConfig.getUsername(), "GMC@3d"));
			mimeMessageHelper.setTo(mailingModel.getTo());
			mimeMessageHelper.setText(mailingModel.getBody(), true);

			mailSender.send(mimeMessageHelper.getMimeMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}	

}
