package dragonb.bearfamily.backend.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="category", schema = "public")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String name;

    @Column(name = "category_color")
    private String color;
}
