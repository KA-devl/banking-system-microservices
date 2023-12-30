package com.banking.bankapi.dto;

import com.banking.bankapi.models.Transaction;
import com.banking.bankapi.models.TransactionType;
import com.banking.bankapi.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionDto {

    private Integer id;

    @Positive
    private BigDecimal amount;

    private TransactionType type;

    @Column(updatable = false)
    private LocalDate transactionDate;

    @NotNull
    @NotBlank
    @NotEmpty
    private String destinationIban;

    private Integer userId;

    public static TransactionDto fromEntity(Transaction transaction){
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .transactionDate(transaction.getTransactionDate())
                .destinationIban(transaction.getDestinationIban())
                .userId(transaction.getUser().getId())
                .build();
    }

    public static Transaction toEntity(TransactionDto transactionDto){
        return Transaction.builder()
                .id(transactionDto.getId())
                .amount(transactionDto.getAmount())
                .type(transactionDto.getType())
                .transactionDate(LocalDate.now())
                .destinationIban(transactionDto.getDestinationIban())
                .user(
                        User.builder()
                        .id(transactionDto.getUserId())
                        .build()
                )
                .build();
    }

}
