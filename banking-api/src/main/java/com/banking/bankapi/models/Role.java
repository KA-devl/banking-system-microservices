package com.banking.bankapi.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role  extends AbstractEntity{

//    @Id
//    @GeneratedValue
//    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
