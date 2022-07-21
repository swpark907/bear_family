package dragonb.bearfamily.backend.model.ledger;

import dragonb.bearfamily.backend.model.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LedgerGroupByCategory {
    private String date;
    private Long price;
    private LedgerColumnKind kind;
    private Category category;
}
