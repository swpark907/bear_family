package dragonb.bearfamily.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dragonb.bearfamily.backend.model.common.Response;
import dragonb.bearfamily.backend.model.email.CheckEmailAuthDTO;
import dragonb.bearfamily.backend.model.email.SendEmailAuthDTO;
import dragonb.bearfamily.backend.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = ".Email API", description = "이메일 관련 기능")
public class EmailController {

    @Autowired
    private EmailService emailService;
    
    @Operation(summary = "send emailauth method", description = "회원가입 이메일 정보를 확인하기 위한 메일을 전송합니다.")
    @PostMapping("/sendEmailauth")
    public Response sendEmailauth(@RequestBody SendEmailAuthDTO sendEmailAuthDTO) {
        Response response = new Response();

        try {
            emailService.sendMessage(sendEmailAuthDTO);

            response.setResponse("success");
            response.setMessage("success sendEmailauth");
            response.setData(true);

        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail sendEmailauth");
            response.setData(false);
        }
        return response;
    }
    
    @Operation(summary = "check emailauth method", description = "메일로 전송받은 인증번호를 확인합니다.")
    @PostMapping("/checkEmailauth")
    public Response checkEmailauth(@RequestBody CheckEmailAuthDTO checkEmailAuthDTO) {
        Response response = new Response();

        try {
            emailService.checkEmailauth(checkEmailAuthDTO);

            response.setData(true);
            response.setResponse("success");
            response.setMessage("success checkEmailToken");

        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail checkEmailToken");
            response.setData(false);
        }
        return response;
    }
}
