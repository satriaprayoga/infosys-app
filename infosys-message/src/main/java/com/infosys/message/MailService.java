package com.infosys.message;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.infosys.message.event.BookingEvent;
import com.infosys.message.event.RegistrationEvent;

@Service
public class MailService {

	private final JavaMailSender mailSender;
	
	private final TemplateEngine templateEngine;
	
	public MailService(JavaMailSender mailSender,TemplateEngine templateEngine) {
		this.mailSender=mailSender;
		this.templateEngine=templateEngine;
	}
	
	public void sendRegistrationEmail(RegistrationEvent event) {
		final Context context=new Context();
		context.setVariable("name", event.getName());
		context.setVariable("confirmUrl", event.getConfirmUrl()+event.getId());
		context.setVariable("message", "Your activation key is: "+event.getKey());
		String body=templateEngine.process("email", context);
		try {
			MimeMessage mail = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
	        helper.setTo(event.getEmail());
	        helper.setSubject("Infosys App");
	        helper.setText(body, true);
	        mailSender.send(mail);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendInvoiceEmail(BookingEvent event) {
		final Context context=new Context();
		context.setVariable("name", event.getName());
		context.setVariable("bookingCode", event.getBookingCode());
		context.setVariable("billingAddress", event.getBillingAddress());
		context.setVariable("packageName", event.getPackageName());
		context.setVariable("totalAmount", event.getTotalAmount());
		context.setVariable("bankAccount", event.getBankAccount());
		String body=templateEngine.process("booking", context);
		try {
			MimeMessage mail = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
	        helper.setTo(event.getEmail());
	        helper.setSubject("Infosys App - Booking Information");
	        helper.setText(body, true);
	        mailSender.send(mail);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
