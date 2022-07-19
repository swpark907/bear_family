package dragonb.bearfamily.backend.model.email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckEmailAuthDTO {
    private String email;
    private String token;
}
