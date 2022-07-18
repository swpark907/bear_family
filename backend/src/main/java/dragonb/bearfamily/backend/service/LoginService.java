package dragonb.bearfamily.backend.service;

import java.util.Date;
import java.util.List;
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
import dragonb.bearfamily.backend.model.TermsDTO;
import dragonb.bearfamily.backend.model.User;
import dragonb.bearfamily.backend.model.UserDTO;
import dragonb.bearfamily.backend.model.UserTerms;
import dragonb.bearfamily.backend.repository.EmailauthRepository;
import dragonb.bearfamily.backend.repository.RefreshtokenRepository;
import dragonb.bearfamily.backend.repository.UserRepository;
import dragonb.bearfamily.backend.repository.UserTermsRepository;
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

    @Autowired
    private UserTermsRepository userTermsRepository;

    public void regist(UserDTO userDTO) throws Exception{
        if(!emailauthRepository.isChecked(userDTO.getEmail())){
            throw new Exception("check emailauth error");
        }

        emailauthRepository.deleteByEmail(userDTO.getEmail());
        
        List<TermsDTO> termsList = userDTO.getTermsList();

        for(int i = 0 ; i < termsList.size() ; i++){
            if(termsList.get(i).isRequired() == true){
                if(termsList.get(i).isChecked() == false){
                    throw new Exception();
                }
            }
        }
        
        Long userId = jwtUserService.save(User.builder()
        .identity(userDTO.getIdentity())
        .password(userDTO.getPassword())
        .email(userDTO.getEmail())
        .name(userDTO.getName())
        .build());

        for(int i = 0 ; i < termsList.size() ; i++){
            userTermsRepository.save(UserTerms.builder()
            .userId(userId)
            .termsId(termsList.get(i).getId())   
            .checked(termsList.get(i).isChecked())
            .build());
        }
    }

    public void checkId(String identity) throws Exception{
        if(identity == "" || identity == null){
            throw new Exception("identity is null");
        }
        else{
            Optional<User> user = userRepository.findByIdentity(identity);
            if(user.isPresent()){
                throw new Exception("already exist");
            }
        }
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
