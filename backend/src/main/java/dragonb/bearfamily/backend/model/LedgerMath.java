package dragonb.bearfamily.backend.model;

import lombok.*;

@Getter
@Setter
public class LedgerMath {
    private String date;
    private Long price;

    public LedgerMath(String date, Long price){
        this.date = date;
        this.price = price;
    }
}
