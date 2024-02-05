package com.beiwel.email.service.impl;

import com.beiwel.email.config.EmailConfig;
import com.beiwel.email.service.EmailService;
import com.beiwel.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Map;


@Component
public class EmailServiceImpl implements EmailService {

  @Autowired
  private JavaMailSender emailSender;

  @Autowired
  private SpringTemplateEngine templateEngine;

  @Profile("!local")
  @Override
  public void sendEmail(Email email)
      throws MessagingException {

    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message,
        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
        StandardCharsets.UTF_8.name());
    Context context = new Context();
    context.setVariables(email.getData());

    String html = templateEngine.process(email.getTemplate(), context);
    helper.setTo(email.getMailTo());
    helper.setText(html, true);
    helper.setSubject(email.getSubject());
    helper.setFrom(EmailConfig.FROM_EMAIL);

    try {
      emailSender.send(message);
    } catch (MailException e) {
      System.out.println("Error porque no existe el email, controlado");
      e.printStackTrace();
      return;
    }
  }


  @Transactional
  public void sendEmailNotification(String subjectEmailNotification, String userEmailToSendEmail, String template, Map<String, Object> emailData) throws MessagingException {
    Email email = Email.builder()
            .from(EmailConfig.FROM_EMAIL)
            .subject(subjectEmailNotification)
            .mailTo(userEmailToSendEmail)
            .template(template)
            .data(emailData)
            .build();

    this.sendEmail(email);
  }
}