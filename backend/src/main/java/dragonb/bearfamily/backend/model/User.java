package dragonb.bearfamily.backend.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.*;

@Entity
@Table(name="user2", schema = "public")
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

	@Column(name = "user_email", unique = true)
	private String email;

    @Column(name = "user_auth")
    private String auth;

    @Column(name = "user_created")
    @CreatedDate
    private Date created;

    @Column(name = "user_updated")
    @LastModifiedDate
    private Date updated;
    
    @Builder
    public User(String identity, String email, String password, String name) {
        this.identity = identity;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
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
