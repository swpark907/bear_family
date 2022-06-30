package dragonb.bearfamily.backend.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.*;

@Entity
@Table(name="ledger", schema = "public")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Ledger {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ledger_id")
    private Long id;

    @Column(name = "ledger_user_identity")
    private String userIdentity;

    @Column(name = "ledger_category_id")
    private int categoryId;

    @Column(name = "ledger_title")
    private String title;

    @Column(name = "ledger_price")
    private Long price;

    @Column(name = "ledger_kind")
    private int kind;

    @Column(name = "ledger_location")
    private String location;

    @Column(name = "ledger_payment")
    private int payment;

    @Column(name = "ledger_description")
    private String description;

    @Column(name = "ledger_date")
    private LocalDateTime date;

    @Column(name = "ledger_created", updatable = false)
    @CreatedDate
    private LocalDateTime created;

    @Column(name = "ledger_updated")
    @LastModifiedDate
    private LocalDateTime updated;
}
