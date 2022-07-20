package dragonb.bearfamily.backend.model.terms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TermsDTO {
    private String title;
    private String content;
    private boolean required;
}
