package za.co.discovery.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.discovery.atm.domain.ATM;
import za.co.discovery.atm.domain.CurrencyConversionRate;

import java.util.List;

public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversionRate, String> {
    CurrencyConversionRate findByCurrencyCode(String currencyCode);
}
