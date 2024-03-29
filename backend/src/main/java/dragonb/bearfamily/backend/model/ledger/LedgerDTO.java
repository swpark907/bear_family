package dragonb.bearfamily.backend.model.ledger;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
public class LedgerDTO {
    private Long categoryId;
    private String title;
    private Long price;
    private int kind;
    private String location;
    private int payment;
    private String description;
    private LocalDateTime date;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
