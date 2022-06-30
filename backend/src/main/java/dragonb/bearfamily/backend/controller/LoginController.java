package dragonb.bearfamily.backend.controller;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import dragonb.bearfamily.backend.configuration.JwtTokenUtil;
import dragonb.bearfamily.backend.model.Emailauth;
import dragonb.bearfamily.backend.model.JwtToken;
import dragonb.bearfamily.backend.model.Refreshtoken;
import dragonb.bearfamily.backend.model.Response;
import dragonb.bearfamily.backend.model.User;
import dragonb.bearfamily.backend.repository.EmailauthRepository;
import dragonb.bearfamily.backend.repository.RefreshtokenRepository;
import dragonb.bearfamily.backend.repository.UserRepository;
import dragonb.bearfamily.backend.service.EmailService;
import dragonb.bearfamily.backend.service.JwtUserService;

@RestController
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private final EmailService emailService;

    @Autowired
    private final EmailauthRepository emailauthRepository;

    @Autowired
    private final JwtUserService jwtUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshtokenRepository refreshtokenRepository;

    @PostMapping("/regist")
    public Response signup(@RequestBody User user) {
        Response response = new Response();
        try {
            if(!emailauthRepository.isChecked(user.getEmail())){
                throw new Exception();
            }

            emailauthRepository.deleteByEmail(user.getEmail());
            
            jwtUserService.save(user);
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

    @PostMapping("/sendEmailauth")
    public Response sendEmailauth(@RequestParam String to) {
        Response response = new Response();

        try {
            String OTP = emailService.sendSimpleMessage(to);
            // String OTP = "123456";
            emailauthRepository.save(Emailauth.builder()
                    .email(to)
                    .token(OTP)
                    .created(LocalDateTime.now()).build());
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

        boolean result = emailauthRepository.isValid(emailauth);

        try {
            if(!result){
                throw new Exception();
            }
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

    @PostMapping("/checkId")
    public Response checkId(@RequestParam String identity) {
        Response response = new Response();

        try {
            if(identity == "" || identity == null){
                response.setMessage("identity is null");
            }
            else{
                jwtUserService.loadUserByUsername(identity);
                response.setMessage("already exist");
            }
            response.setData(identity);
            response.setResponse("fail");
        } catch (Exception e) {
            response.setResponse("success");
            response.setMessage("available");
            response.setData(identity);
        }
        return response;
    }

    @PostMapping(value = "/login")
    public Response createAuthenticationToken(@RequestBody User user) {
        Response response = new Response();

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getIdentity(), user.getPassword()));
            final UserDetails userDetails = jwtUserService
            .loadUserByUsername(user.getIdentity());
            final JwtToken token = jwtTokenUtil.generateToken(userDetails);
            
            Optional<Refreshtoken> refreshtokenByUserIdentity = refreshtokenRepository.findByUserIdentity(user.getIdentity());
            Refreshtoken refreshtoken;
            DecodedJWT decodedJWT = JWT.decode(token.getRefreshToken());
            String guid = decodedJWT.getClaim("roles").toString().substring(2, 38);

            if(refreshtokenByUserIdentity.isPresent()){
                refreshtoken = refreshtokenByUserIdentity.get();
                refreshtoken.setToken(token.getRefreshToken());
                refreshtoken.setGuid(guid);
            }
            else{
                refreshtoken = Refreshtoken.builder()
                .userIdentity(user.getIdentity())
                .token(token.getRefreshToken())
                .guid(guid)
                .build();
            }
            refreshtokenRepository.save(refreshtoken);

            User loginUser = userRepository.findByIdentity(user.getIdentity()).get();
            loginUser.setPassword(null);

            token.setUser(loginUser);

            Date now = new Date();
            token.setAccessTokenExpiredTime(new Date(now.getTime() + JwtTokenUtil.JWT_ACCESS_TOKEN_VALIDITY_TIME));
            response.setResponse("success");
            response.setMessage("success login");
            response.setData(token);
        }
        catch(Exception e){
            response.setResponse("fail");
            response.setMessage("fail login");
            response.setData(e.getMessage());
        }
        return response;
    }

    @PostMapping("/refresh")
    public Response validateRefreshToken(@RequestBody JwtToken jwtToken){
        Response response = new Response();

        try{
            DecodedJWT decodedJWT = JWT.decode(jwtToken.getRefreshToken());
            String guid = decodedJWT.getClaim("roles").toString().substring(2, 38);

            Optional<Refreshtoken> refreshtoken = refreshtokenRepository.findByTokenAndGuid(jwtToken.getRefreshToken(), guid);
            if(!refreshtoken.isPresent()){
                throw new Exception();
            }
            String newAccessToken = jwtTokenUtil.validateRefreshToken(jwtToken);
            if(newAccessToken == null){
                throw new Exception();
            }
            response.setResponse("success");
            response.setMessage("success token refresh");
            response.setData(newAccessToken);
        }
        catch(Exception e){
            response.setResponse("fail");
            response.setMessage("fail token refresh");
            response.setData(e.getMessage());
        }
        return response;
    }
}