package za.co.discovery.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.discovery.atm.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    //Intentionally empty
}
