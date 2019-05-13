package za.co.discovery.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.discovery.atm.domain.ATM;

public interface ATMRepository extends JpaRepository<ATM, Integer> {
    //Intentionally empty
}
