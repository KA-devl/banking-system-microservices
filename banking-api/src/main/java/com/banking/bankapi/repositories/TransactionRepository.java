package com.banking.bankapi.repositories;
import com.banking.bankapi.dto.TransactionSumDetails;
import com.banking.bankapi.models.Transaction;
import com.banking.bankapi.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByUserId(Integer userId);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId")
    BigDecimal findAccountBalance(Integer userId);

    @Query("SELECT MAX(abs(t.amount) ) FROM Transaction t WHERE t.user.id = :userId AND t.type = :transactionType")
    BigDecimal findHighestAmountByTransactionType(Integer userId, TransactionType transactionType);

    //IMPORTANT : THE "AS" KEYWORD IS NECESSARY FOR THE QUERY TO WORK
    @Query("SELECT t.transactionDate as transactionDate, SUM(t.amount) as amount FROM Transaction t WHERE t.user.id = :userId AND t.creationDate BETWEEN :start AND :end GROUP BY t.transactionDate")
    List<TransactionSumDetails> findSumTransactionByDate(LocalDateTime start, LocalDateTime end, Integer userId);
}
