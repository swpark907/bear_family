package dragonb.bearfamily.backend.controller.config;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dragonb.bearfamily.backend.model.EmailToken;
import dragonb.bearfamily.backend.model.Response;
import dragonb.bearfamily.backend.model.UserDto;
import dragonb.bearfamily.backend.repository.EmailTokenRepository;
import dragonb.bearfamily.backend.service.EmailService;
import dragonb.bearfamily.backend.service.JwtUserDetailsService;

@RestController
@RequiredArgsConstructor
public class RegistController {

    private final EmailService emailService;

    private final EmailTokenRepository emailTokenRepository;

    private final JwtUserDetailsService userService;

    @PostMapping("/regist")
    public Response signup(@RequestBody UserDto infoDto) {
        Response response = new Response();

        try {
            userService.save(infoDto);
            emailTokenRepository.deleteById(infoDto.getEmail());
            response.setResponse("success");
            response.setMessage("Successed Regist");
        } catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("Failed Regist");
            response.setData(e.toString());
        }
        return response;
    }

    @PostMapping("/sendEmailToken")
    public Response sendMail(@RequestParam String to) {
        Response response = new Response();

        try {
            String OTP = emailService.sendSimpleMessage(to);
            //String OTP = "123456";
            emailTokenRepository.save(EmailToken.builder()
                .email(to)
                .token(OTP)
                .createdTime(LocalDateTime.now()).build());
            response.setResponse("success");
            response.setMessage("Successed sendEmailToken");

        } catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("Failed sendEmailToken");
            response.setData(e.toString());
        }
        return response;
    }

    @PostMapping("/checkEmailToken")
    public Response checkEmailToken(@RequestBody EmailToken emailToken){
        Response response = new Response();

        try {
            response.setData(emailTokenRepository.isValidToken(emailToken));
            response.setResponse("success");
            response.setMessage("Successed checkEmailToken");

        } catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("Failed checkEmailToken");
            response.setData(e.toString());
        }
        return response;
    }

    @PostMapping("/checkId")
    public Response checkEmailToken(@RequestParam String identity){
        Response response = new Response();

        try {
            userService.loadUserByUsername(identity);
            response.setData(identity);
            response.setResponse("success");
            response.setMessage("Id is exist");

        } catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("ID available");
            response.setData(e.toString());
        }
        return response;
    }
}