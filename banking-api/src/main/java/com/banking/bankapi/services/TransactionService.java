package com.banking.bankapi.services;

import com.banking.bankapi.dto.TransactionDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto>{

    List<TransactionDto> findAllByUserId(Integer userId);
}
