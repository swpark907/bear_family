package dragonb.bearfamily.backend.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import dragonb.bearfamily.backend.model.login.User;
import dragonb.bearfamily.backend.repository.UserRepository;

@Service
public class CommonService {
    
    @Autowired
    public UserRepository userRepository;

    public String getUserIdentity(HttpServletRequest request){
        String accesstoken = request.getHeader("authorization").substring(7);
        DecodedJWT decodedJWT = JWT.decode(accesstoken);
        return decodedJWT.getSubject();
    }

    public boolean isAdmin(HttpServletRequest request) throws Exception{
        String identity = getUserIdentity(request);
        Optional<User> user = userRepository.findByIdentity(identity);
        int gradeId;
        if(user.isPresent()){
            gradeId = user.get().getGrade().getId();
        }
        else{
            throw new Exception();
        }

        if(gradeId == 2){
            return true;
        }
        else{
            return false;
        }
    }
}
