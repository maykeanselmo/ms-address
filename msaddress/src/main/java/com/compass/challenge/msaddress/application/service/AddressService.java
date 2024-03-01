package com.compass.challenge.msaddress.application.service;

import com.compass.challenge.msaddress.application.consumer.CepConsumerFeign;
import com.compass.challenge.msaddress.application.dto.IdDto;
import com.compass.challenge.msaddress.application.dto.mapper.AddressMapper;
import com.compass.challenge.msaddress.domain.Address;
import com.compass.challenge.msaddress.infra.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final CepConsumerFeign cepConsumerFeign;

    public IdDto saveAddress(String cep) {
        Address existingAddress = addressRepository.getByCep(cep);
        if (existingAddress == null) {
            Address address = cepConsumerFeign.getAddressByCep(cep);
            Address addressSaved = addressRepository.save(address);
            return AddressMapper.toIdDto(addressSaved);
        } else {
            return AddressMapper.toIdDto(existingAddress);
        }


    }


    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Nenhum endereço foi encontrado com este id: " + id)
        );
    }
}
