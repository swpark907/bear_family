package dragonb.bearfamily.backend.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="ledger_column_payment", schema = "public")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LedgerColumnPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ledger_column_payment_id")
    private int id;

    @Column(name = "ledger_column_payment_name")
    private String name;
}
