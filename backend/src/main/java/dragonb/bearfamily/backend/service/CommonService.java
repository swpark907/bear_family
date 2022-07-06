package dragonb.bearfamily.backend.service;

import javax.servlet.http.HttpServletRequest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class CommonService {
    
    public static String getUserIdentity(HttpServletRequest request){
        String accesstoken = request.getHeader("authorization").substring(7);
        DecodedJWT decodedJWT = JWT.decode(accesstoken);
        return decodedJWT.getSubject();
    }

}
