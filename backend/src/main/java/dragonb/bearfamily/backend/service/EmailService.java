package dragonb.bearfamily.backend.service;

import lombok.AllArgsConstructor;

import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
 
@Service
@AllArgsConstructor
public class EmailService {
 
    @Autowired
    JavaMailSender emailSender;
 
    @Autowired
    TemplateEngine templateEngine;
    
    public static String OTP = createKey();

    private MimeMessage createMessage(String to)throws Exception{
        OTP = createKey();
        
        String mailSubject = "회원가입 이메일 인증";
        String from = "GGOMGGOM";

        MimeMessage  message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        helper.setSubject(mailSubject);
        helper.setTo(to);

        Context context = new Context();
        context.setVariable("OTP", OTP);
        
        String html = templateEngine.process("email.html", context);
        helper.setText(html, true);
        helper.setFrom(new InternetAddress(to, from));
        
        emailSender.send(message);
        
        return message;
    }
 
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) {
            key.append((rnd.nextInt(10)));
        }

        return key.toString();
    }

    public String sendSimpleMessage(String to)throws Exception {
        MimeMessage message = createMessage(to);
        try{
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return OTP;
    }
}