package dragonb.bearfamily.backend.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="grade", schema = "public")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private int id;

    @Column(name = "grade_name")
    private String name;
}
