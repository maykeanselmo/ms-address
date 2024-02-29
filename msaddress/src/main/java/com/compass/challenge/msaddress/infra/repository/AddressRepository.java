package com.compass.challenge.msaddress.infra.repository;

import com.compass.challenge.msaddress.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
