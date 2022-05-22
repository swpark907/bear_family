package dragonb.bearfamily.backend.controller.config;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dragonb.bearfamily.backend.model.Response;
import dragonb.bearfamily.backend.model.UserDto;
import dragonb.bearfamily.backend.service.JwtUserDetailsService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final JwtUserDetailsService userService;

    @PostMapping("/regist")
    public Response signup(@RequestBody UserDto infoDto) { // 회원 추가
        Response response = new Response();

        try {
            userService.save(infoDto);
            response.setResponse("success");
            response.setMessage("Successed Regist");
        } catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("Failed Regist");
            response.setData(e.toString());
        }
        return response;
    }

}