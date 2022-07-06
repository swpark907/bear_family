package dragonb.bearfamily.backend.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import dragonb.bearfamily.backend.configuration.JwtTokenUtil;
import dragonb.bearfamily.backend.model.JwtToken;
import dragonb.bearfamily.backend.model.Refreshtoken;
import dragonb.bearfamily.backend.model.User;
import dragonb.bearfamily.backend.repository.EmailauthRepository;
import dragonb.bearfamily.backend.repository.RefreshtokenRepository;
import dragonb.bearfamily.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    
    @Autowired
    EmailauthRepository emailauthRepository;
    
    @Autowired
    private final JwtUserService jwtUserService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RefreshtokenRepository refreshtokenRepository;

    public void regist(User user) throws Exception{
        if(!emailauthRepository.isChecked(user.getEmail())){
            throw new Exception();
        }

        emailauthRepository.deleteByEmail(user.getEmail());
        
        jwtUserService.save(user);
    }

    public Optional<User> checkId(String identity) throws Exception{
        //jwtUserService.loadUserByUsername(identity);
        return userRepository.findByIdentity(identity);
    }

    public JwtToken login(User user) throws Exception{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getIdentity(), user.getPassword()));
            final UserDetails userDetails = jwtUserService
            .loadUserByUsername(user.getIdentity());
            final JwtToken token = jwtTokenUtil.generateToken(userDetails);
            
            Optional<Refreshtoken> refreshtokenByUserIdentity = refreshtokenRepository.findByUserIdentity(user.getIdentity());
            Refreshtoken refreshtoken;
            DecodedJWT decodedJWT = JWT.decode(token.getRefreshToken());
            String uuid = decodedJWT.getClaim("uuid").toString().substring(1, 37);

            if(refreshtokenByUserIdentity.isPresent()){
                refreshtoken = refreshtokenByUserIdentity.get();
                refreshtoken.setToken(token.getRefreshToken());
                refreshtoken.setUuid(uuid);
            }
            else{
                refreshtoken = Refreshtoken.builder()
                .userIdentity(user.getIdentity())
                .token(token.getRefreshToken())
                .uuid(uuid)
                .build();
            }
            refreshtokenRepository.save(refreshtoken);

            User loginUser = userRepository.findByIdentity(user.getIdentity()).get();
            loginUser.setPassword(null);

            token.setUser(loginUser);

            Date now = new Date();
            token.setAccessTokenExpiredTime(new Date(now.getTime() + JwtTokenUtil.JWT_ACCESS_TOKEN_VALIDITY_TIME));

            return token;
    }

    public String refresh(JwtToken jwtToken) throws Exception{
        DecodedJWT decodedJWT = JWT.decode(jwtToken.getRefreshToken());
        String uuid = decodedJWT.getClaim("uuid").toString().substring(1, 37);

        Optional<Refreshtoken> refreshtoken = refreshtokenRepository.findByTokenAndUuid(jwtToken.getRefreshToken(), uuid);
        if(!refreshtoken.isPresent()){
            throw new Exception();
        }
        String newAccessToken = jwtTokenUtil.validateRefreshToken(jwtToken);
        if(newAccessToken == null){
            throw new Exception();
        }
        return newAccessToken;
    }
}
