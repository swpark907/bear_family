package dragonb.bearfamily.backend.model.ledger;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class LedgerGroupByDate {
    private String date;
    private Long price;
}
