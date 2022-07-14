package dragonb.bearfamily.backend.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;

@Getter
@Setter
public class LedgerEx {
    private Long id;
    private String userIdentity;
    private Long categoryId;
    private String title;
    private Long price;
    private int kind;
    private String location;
    private int payment;
    private String description;
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
