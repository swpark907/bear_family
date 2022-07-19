package dragonb.bearfamily.backend.model.email;

import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.*;


@Entity
@Table(name="emailauth", schema = "public")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Emailauth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emailauth_id")
	private String id;
	
	@Column(name = "emailauth_token")
	private String token;

    @Column(name = "emailauth_created")
    private LocalDateTime created;

    @Column(name = "emailauth_email")
	private String email;

    @Column(name = "emailauth_checked")
    private boolean checked;
}
