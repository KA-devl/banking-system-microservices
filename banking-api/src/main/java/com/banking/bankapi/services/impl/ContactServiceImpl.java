package com.banking.bankapi.services.impl;

import com.banking.bankapi.dto.ContactDto;
import com.banking.bankapi.repositories.ContactRepository;
import com.banking.bankapi.services.ContactService;
import com.banking.bankapi.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ObjectsValidator<ContactDto> validator;

    @Override
    public Integer save(ContactDto dto) {
        return contactRepository.save(ContactDto.toEntity(dto)).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return contactRepository.findAll()
                .stream()
                .map(contact -> ContactDto.fromEntity(contact))
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return contactRepository.findById(id)
                .map(contact -> ContactDto.fromEntity(contact))
                .orElseThrow(() -> new EntityNotFoundException("Contact not found with id: " + id));
    }

    @Override
    public void delete(Integer id) {
        //todo check delete
        contactRepository.deleteById(id);

    }

    @Override
    public List<ContactDto> findAllByUserId(Integer userId) {
        return contactRepository.findAllByUserId(userId)
                .stream()
                .map(contact -> ContactDto.fromEntity(contact))
                .collect(Collectors.toList());
    }
}
