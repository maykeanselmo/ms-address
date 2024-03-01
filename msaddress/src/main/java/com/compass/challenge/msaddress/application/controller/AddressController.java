package com.compass.challenge.msaddress.application.controller;

import com.compass.challenge.msaddress.application.dto.AddressResponseDto;
import com.compass.challenge.msaddress.application.dto.IdDto;
import com.compass.challenge.msaddress.application.dto.mapper.AddressMapper;
import com.compass.challenge.msaddress.application.service.AddressService;
import com.compass.challenge.msaddress.domain.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/addresses")
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/{cep}")
    public ResponseEntity<IdDto> createAddress(@PathVariable ("cep") String cep) {
        IdDto idDto = addressService.saveAddress(cep);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(idDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> getAddressById(@PathVariable("id") Long id) {
        Address address = addressService.getAddressById(id);
        return ResponseEntity.ok(AddressMapper.toAddresResponseDto(address));

    }


}

