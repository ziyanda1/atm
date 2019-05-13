package za.co.discovery.atm.repository;

import za.co.discovery.atm.web.dto.FinancialPositionPerClient;
import za.co.discovery.atm.web.dto.HighestBalanceAccountPerClient;

import java.util.List;

public interface ReportRepository {
    List<HighestBalanceAccountPerClient> findHighestBalanceAccountsPerClient();
}
