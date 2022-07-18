package dragonb.bearfamily.backend.model;

import java.util.ArrayList;
import java.util.List;

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
    private Long id;

    @Column(name = "terms_title")
    private String title;

    @Column(name = "terms_content")
    private String content;

    @Column(name = "terms_required")
    private boolean required;

    //private boolean checked;
}
