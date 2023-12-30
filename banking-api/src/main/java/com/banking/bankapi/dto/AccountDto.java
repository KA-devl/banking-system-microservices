package com.banking.bankapi.dto;

import com.banking.bankapi.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountDto {

    private Integer id;

    private String iban;

    //We use this to get the user details since we are using @OneToOne
    private UserDto user; // We can also user id here

    public static AccountDto fromEntity(Account account) {
        return AccountDto.builder()
                .iban(account.getIban())
                .id(account.getId())
                .user(UserDto.fromEntity(account.getUser()))
                .build();

    }
    public static Account toEntity(AccountDto accountDto) {
        return Account.builder()
                .iban(accountDto.getIban())
                .id(accountDto.getId())
                .user(UserDto.toEntity(accountDto.getUser()))
                .build();
    }
}
