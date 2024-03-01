package com.compass.challenge.msaddress.application.dto.mapper;

import com.compass.challenge.msaddress.application.dto.AddressResponseDto;
import com.compass.challenge.msaddress.application.dto.IdDto;
import com.compass.challenge.msaddress.domain.Address;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressMapper {

    public  static IdDto toIdDto(Address address){
        return new ModelMapper().map(address, IdDto.class);
    }
    public  static AddressResponseDto toAddresResponseDto(Address address){
        return new ModelMapper().map(address, AddressResponseDto.class);
    }


}
