package za.co.discovery.atm.service;

import za.co.discovery.atm.web.dto.HighestBalanceAccountPerClient;

import java.util.List;

public interface ReportService {
    List<HighestBalanceAccountPerClient> getHighestBalanceAccountsPerClient();
}
