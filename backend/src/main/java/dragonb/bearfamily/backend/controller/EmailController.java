package dragonb.bearfamily.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dragonb.bearfamily.backend.model.Emailauth;
import dragonb.bearfamily.backend.model.Response;
import dragonb.bearfamily.backend.service.EmailService;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;
    
    @PostMapping("/sendEmailauth")
    public Response sendEmailauth(@RequestParam String to) {
        Response response = new Response();

        try {
            emailService.sendMessage(to);

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
    
    @PostMapping("/checkEmailauth")
    public Response checkEmailauth(@RequestBody Emailauth emailauth) {
        Response response = new Response();

        boolean result = emailService.checkEmailauth(emailauth);

        try {
            response.setData(result);
            response.setResponse("success");
            response.setMessage("success checkEmailToken");

        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail checkEmailToken");
            response.setData(result);
        }
        return response;
    }
}
