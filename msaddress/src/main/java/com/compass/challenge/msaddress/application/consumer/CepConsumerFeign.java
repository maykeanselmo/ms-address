package com.compass.challenge.msaddress.application.consumer;

import com.compass.challenge.msaddress.domain.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cep-consumer", url = "https://viacep.com.br/ws")
public interface CepConsumerFeign {
    @GetMapping(value = "/{cep}/json")
    Address getAddressByCep(@PathVariable("cep") String cep);
}
