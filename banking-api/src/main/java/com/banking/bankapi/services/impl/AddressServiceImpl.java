package com.banking.bankapi.services.impl;

import com.banking.bankapi.dto.AddressDto;
import com.banking.bankapi.repositories.AddressRepository;
import com.banking.bankapi.services.AddressService;
import com.banking.bankapi.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final ObjectsValidator<AddressDto> validator;

    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        return addressRepository.save(AddressDto.toEntity(dto)).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(address -> AddressDto.fromEntity(address))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return addressRepository.findById(id)
                .map(address -> AddressDto.fromEntity(address))
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));
    }

    @Override
    public void delete(Integer id) {
        //todo check delete
        addressRepository.deleteById(id);

    }
}
