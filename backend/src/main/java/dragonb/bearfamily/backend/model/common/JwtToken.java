package dragonb.bearfamily.backend.model.common;

import java.util.Date;

import dragonb.bearfamily.backend.model.login.User;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String key;
    private User user;
    private Date accessTokenExpiredTime;
}
