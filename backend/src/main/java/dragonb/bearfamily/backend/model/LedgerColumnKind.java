package dragonb.bearfamily.backend.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="ledger_column_kind", schema = "public")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LedgerColumnKind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ledger_column_kind_id")
    private int id;

    @Column(name = "ledger_column_kind_name")
    private String name;
}
