package com.banking.bankapi.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact extends AbstractEntity {

//    @Id
//    @GeneratedValue
//    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String iban;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
