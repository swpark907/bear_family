package dragonb.bearfamily.backend.model.terms;

import lombok.*;

@Getter
@Setter
public class UserTermsDTO {
    private Long id;
    private boolean required;
    private boolean checked;
}
