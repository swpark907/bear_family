package dragonb.bearfamily.backend.model;

import javax.validation.constraints.NotEmpty;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDto{

    @NotEmpty
	private String identity;
    private String email;
	private String password;
    private String name;
    
}