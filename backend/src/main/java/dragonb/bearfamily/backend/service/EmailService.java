package dragonb.bearfamily.backend.service;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import dragonb.bearfamily.backend.model.Emailauth;
import dragonb.bearfamily.backend.repository.EmailauthRepository;
 
@Service
public class EmailService {
 
    @Autowired
    private JavaMailSender emailSender;
 
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private EmailauthRepository emailauthRepository;

    public void sendMessage(String to)throws Exception{
        String OTP = createKey();

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
        
        emailauthRepository.save(Emailauth.builder()
        .email(to)
        .token(OTP)
        .created(LocalDateTime.now()).build());
    }
 
    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) {
            key.append((rnd.nextInt(10)));
        }

        return key.toString();
    }

    public void checkEmailauth(Emailauth emailauth) throws Exception{
        boolean result = emailauthRepository.isValid(emailauth);
        if(!result){
            throw new Exception();
        }
    }
}