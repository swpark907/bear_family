package dragonb.bearfamily.backend.model;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class LedgerEx {
    private Long id;
    private String userIdentity;
    private int categoryId;
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
