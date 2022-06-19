package dragonb.bearfamily.backend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dragonb.bearfamily.backend.model.User;
import dragonb.bearfamily.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

// DB에서 UserDetail을 얻어와 AuthenticationManager에게 제공하는 역할 수행
@Service
@RequiredArgsConstructor
public class JwtUserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identity) throws UsernameNotFoundException {
        return userRepository.findByIdentity(identity).orElseThrow(() -> new UsernameNotFoundException(identity));
    }

    @Transactional
	public Long save(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(user.getPassword() != ""){
            user.setPassword(encoder.encode(user.getPassword()));
        }

		return userRepository.save(User.builder()
                .identity(user.getIdentity())
				.email(user.getEmail())
				.name(user.getName())
				.password(user.getPassword()).build()).getId();
	}
}