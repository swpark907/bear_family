package dragonb.bearfamily.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dragonb.bearfamily.backend.configuration.JwtRequest;
import dragonb.bearfamily.backend.configuration.JwtResponse;
import dragonb.bearfamily.backend.configuration.JwtTokenUtil;
import dragonb.bearfamily.backend.model.Result;
import dragonb.bearfamily.backend.model.User;
import dragonb.bearfamily.backend.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
            .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Autowired
    UserRestController userRestController;

    @RequestMapping(value = "/signon", method = RequestMethod.POST)
    public boolean signon(@RequestBody User user) throws Exception {
        Result result = userRestController.createUser(user);
        System.out.println("AAAAAAAAAA");
        System.out.println("AAAAAAAAAB");
        System.out.println("AAAAAAAAAC");
        System.out.println("AAAAAAAAAD");
        System.out.println("AAAAAAAAAE");
        System.out.println(result.getPayload());
        return true;
    }
}
