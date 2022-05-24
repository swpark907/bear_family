package dragonb.bearfamily.backend.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dragonb.bearfamily.backend.model.EmailToken;

public interface EmailTokenRepository extends JpaRepository<EmailToken, String> {

    @Query(value="select count(*) from email_token where email_token_created_time > :threshold "
    + "and user_email = :email and email_token = :token", nativeQuery = true)
    int existValidToken(@Param("threshold") LocalDateTime threshold,
    @Param("email") String email, @Param("token") String token);

    default boolean isValidToken(EmailToken emailToken) {
        long minutesGap = 1;
        
        return existValidToken(LocalDateTime.now().minusMinutes(minutesGap), 
        emailToken.getEmail(), emailToken.getToken()) > 0;
    }

}
