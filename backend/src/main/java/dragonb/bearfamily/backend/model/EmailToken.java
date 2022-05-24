package dragonb.bearfamily.backend.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="email_token", schema = "public")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailToken {
    
    @Id
	@Column(name = "user_email")
	private String email;
	
	@Column(name = "email_token")
	private String token;

    @Column(name = "email_token_created_time")
    private LocalDateTime createdTime;
}
