package dragonb.bearfamily.backend.repository;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dragonb.bearfamily.backend.model.Emailauth;

public interface EmailauthRepository extends JpaRepository<Emailauth, String> {

    @Query(value="select count(*) from emailauth where emailauth_created > :threshold "
    + "and emailauth_email = :email and emailauth_token = :token", nativeQuery = true)
    int existValidToken(@Param("threshold") LocalDateTime threshold,
    @Param("email") String email, @Param("token") String token);

    default boolean isValid(Emailauth emailauth) {
        long minutesGap = 3;
        
        updateEmailauthChecked(emailauth.getEmail(), emailauth.getToken());

        return existValidToken(LocalDateTime.now().minusMinutes(minutesGap), 
        emailauth.getEmail(), emailauth.getToken()) > 0;
    }

    @Transactional
    @Modifying
    @Query(value = "delete from emailauth where emailauth_email = :email", nativeQuery = true)
    void deleteByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "update emailauth set emailauth_checked = true where emailauth_email = :email and emailauth_token = :token", nativeQuery = true)
    void updateEmailauthChecked(@Param("email") String email, @Param("token") String token);

    @Query(value="select count(*) from emailauth where emailauth_created > :threshold and emailauth_email = :email and emailauth_checked = true", nativeQuery = true)
    int existCheckedEmailauth(@Param("threshold") LocalDateTime threshold, @Param("email") String email);

    default boolean isChecked(String email) {
        long minutesGap = 3;

        return existCheckedEmailauth(LocalDateTime.now().minusMinutes(minutesGap), email) > 0;
    }
}
