package dragonb.bearfamily.backend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="refreshtoken", schema = "public")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Refreshtoken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refreshtoken_id")
    private Long id;

	@Column(name = "refreshtoken_user_identity", unique = true)
	private String userIdentity;

    @Column(name = "refreshtoken_token")
	private String token;

    @Column(name = "refreshtoken_uuid")
    private String uuid;
}
