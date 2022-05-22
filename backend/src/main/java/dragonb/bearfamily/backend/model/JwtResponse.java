package dragonb.bearfamily.backend.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 응답 객체
@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8724879111956236941L;
    private final String jwttoken;
}
