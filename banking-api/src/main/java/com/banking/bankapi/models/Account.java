package com.banking.bankapi.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account extends AbstractEntity {
//    @Id
//    @GeneratedValue
//    private Integer id;

    private String iban;

//    private LocalDateTime creationDate;
//
//    private LocalDateTime lastUpdated;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
