package dragonb.bearfamily.backend.model;

import lombok.*;

@Getter
@Setter
public class UserDTO {
	// User
	private String identity;
	private String password;
	private String name;
	private String email;

	//Terms
    private int id;
    private boolean require;
	private boolean checked;
}
