package dragonb.bearfamily.backend.model.login;

import java.util.List;

import dragonb.bearfamily.backend.model.TermsDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistDTO {
    // User
	private String identity;
	private String password;
	private String name;
	private String email;

	//Terms
    private List<TermsDTO> termsList;
}
