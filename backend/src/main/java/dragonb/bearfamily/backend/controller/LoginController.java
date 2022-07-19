package dragonb.bearfamily.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dragonb.bearfamily.backend.model.common.JwtToken;
import dragonb.bearfamily.backend.model.common.Response;
import dragonb.bearfamily.backend.model.login.CheckIdDTO;
import dragonb.bearfamily.backend.model.login.LoginDTO;
import dragonb.bearfamily.backend.model.login.RefreshDTO;
import dragonb.bearfamily.backend.model.login.RegistDTO;
import dragonb.bearfamily.backend.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "..Login API", description = "회원가입 / 로그인 관련 기능")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Operation(summary = "regist method", description = "약관 동의 여부와 함께 아이디, 비밀번호, 이름, 이메일 정보를 등록합니다.")
    @PostMapping("/regist")
    public Response signup(@RequestBody RegistDTO registDTO) {
        Response response = new Response();
        
        try {
            loginService.regist(registDTO);
            
            response.setResponse("success");
            response.setMessage("success Regist");
            response.setData(true);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail Regist");
            response.setData(false);
        }
        return response;
    }

    @Operation(summary = "check id method", description = "등록하려는 아이디의 존재 유무를 확인합니다.")
    @PostMapping("/checkId")
    public Response checkId(@RequestBody CheckIdDTO checkIdDTO) {
        Response response = new Response();

        try {
            loginService.checkId(checkIdDTO);

            response.setResponse("success");
            response.setMessage("available");
            response.setData(checkIdDTO.getIdentity());
        } catch (Exception e) {
            response.setData(checkIdDTO.getIdentity());
            response.setResponse("fail");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    //@SwaggerInterface
    @Operation(summary = "login method", description = "아이디와 비밀번호를 이용해 토큰을 부여합니다.")
    @PostMapping(value = "/login")
    public Response createAuthenticationToken(@RequestBody LoginDTO loginDTO) {
        Response response = new Response();

        try{
            JwtToken token = loginService.login(loginDTO);
            
            response.setResponse("success");
            response.setMessage("success login");
            response.setData(token);
        }
        catch(Exception e){
            response.setResponse("fail");
            response.setMessage("fail login");
            response.setData(null);
        }
        return response;
    }

    @Operation(summary = "refresh token method", description = "리프레쉬 토큰을 검증한 후 액세스 토큰을 재발급합니다.")
    @PostMapping("/refresh")
    public Response validateRefreshToken(@RequestBody RefreshDTO refreshDTO){
        Response response = new Response();

        try{
            String newAccessToken = loginService.refresh(refreshDTO);
            
            response.setResponse("success");
            response.setMessage("success token refresh");
            response.setData(newAccessToken);
        }
        catch(Exception e){
            response.setResponse("fail");
            response.setMessage("fail token refresh");
            response.setData(null);
        }
        return response;
    }
}