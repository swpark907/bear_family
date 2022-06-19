package dragonb.bearfamily.backend.model;

import java.util.Date;

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
