package za.co.discovery.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.discovery.atm.domain.ATM;
import za.co.discovery.atm.domain.ATMAllocation;

import java.util.List;

public interface ATMAllocationRepository extends JpaRepository<ATMAllocation, Integer> {
    List<ATMAllocation> findByAtm(ATM atm);
}

