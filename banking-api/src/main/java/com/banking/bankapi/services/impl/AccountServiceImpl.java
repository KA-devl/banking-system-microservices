package com.banking.bankapi.services.impl;

import com.banking.bankapi.dto.AccountDto;
import com.banking.bankapi.exceptions.OperationNonPermittedException;
import com.banking.bankapi.models.Account;
import com.banking.bankapi.repositories.AccountRepository;
import com.banking.bankapi.services.AccountService;
import com.banking.bankapi.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final ObjectsValidator<AccountDto> validator;

    @Override
    @Transactional
    public Integer save(AccountDto dto) {
        //block iban update -> iban cannot be changed once created
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto); //transform dto to entity
        //verify if user have already an account
        boolean userHasAlreadyAccount = accountRepository.findByUserId(account.getUser().getId()).isPresent();
        if (userHasAlreadyAccount && account.getUser().isActive()) {
            throw new OperationNonPermittedException(
                    "User already has an account",
                    "save account",
                    "account service",
                    "Account creation"
            );
        }
        //GENERATE RANDOM IBAN when creating a new account else do not update the iban
        if(account.getIban() == null) {
            account.setIban(generateRandomIban());
        }
        return accountRepository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(account -> AccountDto.fromEntity(account))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return accountRepository.findById(id)
                .map(account -> AccountDto.fromEntity(account))
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
    }

    @Override
    public void delete(Integer id) {
        //todo check delete account
        accountRepository.deleteById(id);
    }

    private String generateRandomIban() {
        //generate iban (We use iban4j dependency)
        String iban = Iban.random(CountryCode.FR).toFormattedString();
        //check if iban already exists
        boolean ibanExists = accountRepository.findByIban(iban).isPresent();

        //if exists -> generate another one
        if (ibanExists) {
            generateRandomIban();
        }

        //if not exists -> return iban

        return iban;
    }
}
