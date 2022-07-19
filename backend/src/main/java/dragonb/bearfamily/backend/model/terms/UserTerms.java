package dragonb.bearfamily.backend.model.terms;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="user_terms", schema = "public")
@Getter @Setter
@Builder
@IdClass(UserTermsId.class)
@AllArgsConstructor
@NoArgsConstructor
public class UserTerms {
    
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "terms_id")
    private Long termsId;

    @Column(name = "user_terms_checked")
    private boolean checked;
}
