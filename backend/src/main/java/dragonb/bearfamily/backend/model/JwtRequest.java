package dragonb.bearfamily.backend.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 요청 객체
@Setter
@Getter
@AllArgsConstructor
public class JwtRequest implements Serializable{
    
    private static final long serialVersionUID = 1226471582095105734L;

    private String identity;
    private String password;

    //need default constructor for JSON Parsing
    public JwtRequest() {   }
}
