package com.beiwel.email.service;

import com.beiwel.model.Email;
import javax.mail.MessagingException;
import java.util.Map;

public interface EmailService {

  void sendEmail(Email email) throws MessagingException;
  void sendEmailNotification(String subjectEmailNotification, String userEmailToSendEmail, String template, Map<String, Object> emailData)throws MessagingException;


}
