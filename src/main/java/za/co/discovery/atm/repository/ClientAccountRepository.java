package za.co.discovery.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.discovery.atm.domain.ClientAccount;

import java.util.List;
import java.util.Optional;

public interface  ClientAccountRepository extends JpaRepository<ClientAccount, String> {
    @Query("SELECT ca FROM ClientAccount ca WHERE ca.client.clientId = :clientId AND ca.accountType.transactional=:transactional ORDER BY ca.displayBalance DESC")
    Optional<List<ClientAccount>> findAccountsByClientId(@Param("clientId") Integer clientId, @Param("transactional") boolean transactional);
}
