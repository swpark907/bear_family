package dragonb.bearfamily.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dragonb.bearfamily.backend.model.JwtToken;
import dragonb.bearfamily.backend.model.Response;
import dragonb.bearfamily.backend.model.User;
import dragonb.bearfamily.backend.model.UserDTO;
import dragonb.bearfamily.backend.service.LoginService;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/regist")
    public Response signup(@RequestBody UserDTO userDTO) {
        Response response = new Response();
        
        try {
            loginService.regist(userDTO);
            
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

    @PostMapping("/checkId")
    public Response checkId(@RequestParam String identity) {
        Response response = new Response();

        try {
            if(identity == "" || identity == null){
                response.setMessage("identity is null");
                throw new Exception();
            }
            else{
                Optional<User> user = loginService.checkId(identity);
                if(user.isPresent()){
                    response.setMessage("already exist");
                    throw new Exception();
                }
            }
            response.setResponse("success");
            response.setMessage("available");
            response.setData(identity);
        } catch (Exception e) {
            response.setData(identity);
            response.setResponse("fail");
        }
        return response;
    }

    @PostMapping(value = "/login")
    public Response createAuthenticationToken(@RequestBody User user) {
        Response response = new Response();

        try{
            JwtToken token = loginService.login(user);
            
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

    @PostMapping("/refresh")
    public Response validateRefreshToken(@RequestBody JwtToken jwtToken){
        Response response = new Response();

        try{
            String newAccessToken = loginService.refresh(jwtToken);
            
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