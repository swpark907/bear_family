package dragonb.bearfamily.backend.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
@Entity
@Table(name="user", schema = "public")
@Getter
@Setter
@ToString
@DynamicInsert
@DynamicUpdate
public class User {
	
	@Id
	@Column(name="user_identity")
	private String identity;
	
	@Column(name="user_password")
	private String password;

	@Column(name="user_name")
	private String name;

	@Column(name="user_tagno")
	private Integer tagno;

	@Column(name="user_email")
	private String email;
}