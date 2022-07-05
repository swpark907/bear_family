package dragonb.bearfamily.backend.model;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;

import lombok.*;

@Entity
@Table(name="terms", schema = "public")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Terms {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_id")
    private int id;

    @Column(name = "terms_content")
    private String content;

    @Column(name = "terms_require")
    private boolean require;
}
