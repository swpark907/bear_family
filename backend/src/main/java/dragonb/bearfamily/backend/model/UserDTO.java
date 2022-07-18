package dragonb.bearfamily.backend.model;

import java.util.List;

import lombok.*;

@Getter
@Setter
public class UserDTO {
	// User
	private String id;
	private String identity;
	private String password;
	private String name;
	private String email;

	//Terms
    private List<TermsDTO> termsList;
}
