package dragonb.bearfamily.backend.model.login;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.*;

@Entity
@Table(name="user", schema = "public")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

	@Column(name = "user_identity", unique = true)
	private String identity;
	
	@Column(name = "user_password")
	private String password;
 
	@Column(name = "user_name")
	private String name;

	@Column(name = "user_email")
	private String email;

    //@Column(name = "user_grade_id")
    //private String gradeId;
    @OneToOne
    @JoinColumn(name = "user_grade_id", referencedColumnName = "grade_id")
    private Grade gradeId;

    @Column(name = "user_created", updatable = false)
    @CreatedDate
    private LocalDateTime created;

    @Column(name = "user_updated")
    @LastModifiedDate
    private LocalDateTime updated;
    
    // @Column(name = "user_auth")
    // private String auth;

    @Builder
    public User(String identity, String email, String password, String name) {
        this.identity = identity;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Set<GrantedAuthority> roles = new HashSet<>();
        // for (String role : auth.split(",")) {
        //     roles.add(new SimpleGrantedAuthority(role));
        // }
        return null;
    }

    @Override
    public String getUsername() {
        return identity;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
