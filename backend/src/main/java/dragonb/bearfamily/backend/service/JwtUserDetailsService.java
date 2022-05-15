package dragonb.bearfamily.backend.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService{
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // test, test
        //if ("user_id".equals(username)) {
        if ("test".equals(username)) {
            //return new User("user_id", "$2a$10$m/enYHaLsCwH2dKMUAtQp.ksGOA6lq7Fd2pnMb4L.yT4GyeAPRPyS",
            return new User("test", "$2a$10$uaQAmWiWo/dBW4miKFQc.eOv3793NkzK93avUQkNIyBLjrKkA6EWm",
                new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
    
}