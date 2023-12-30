package com.banking.bankapi.services.impl;

import com.banking.bankapi.dto.TransactionDto;
import com.banking.bankapi.models.Transaction;
import com.banking.bankapi.models.TransactionType;
import com.banking.bankapi.repositories.TransactionRepository;
import com.banking.bankapi.services.TransactionService;
import com.banking.bankapi.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ObjectsValidator<TransactionDto> validator;

    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        //finds the transaction type and multiplies the amount by 1 or -1
        BigDecimal transactionMultiplier = BigDecimal.valueOf(transactionType(transaction.getType()));
        //sets the amount to the new amount
        BigDecimal amount = transaction.getAmount().multiply(transactionMultiplier);
        transaction.setAmount(amount);
        return transactionRepository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(transaction -> TransactionDto.fromEntity(transaction))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return transactionRepository.findById(id)
                .map(transaction -> TransactionDto.fromEntity(transaction))
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + id));
    }

    @Override
    public void delete(Integer id) {
        //todo check delete
        transactionRepository.deleteById(id);

    }

    private int transactionType(TransactionType type) {
        //deposit is 1, withdraw is -1
        return TransactionType.TRANSFER.equals(type) ? -1 : 1;
    }

    @Override
    public List<TransactionDto> findAllByUserId(Integer userId) {
        return transactionRepository.findAllByUserId(userId)
                .stream()
                .map(transaction -> TransactionDto.fromEntity(transaction))
                .collect(java.util.stream.Collectors.toList());
    }
}
