package dragonb.bearfamily.backend.model;
 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
@Entity
@Table(name="user", schema = "public")
@Getter
@Setter
@ToString
public class User {
	
	@Id
	private String id;
	
	private String password;
	private String name;
	private String email;
}