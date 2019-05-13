package za.co.discovery.atm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery.atm.repository.*;
import za.co.discovery.atm.service.ReportService;
import za.co.discovery.atm.web.dto.FinancialPositionPerClient;
import za.co.discovery.atm.web.dto.HighestBalanceAccountPerClient;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<HighestBalanceAccountPerClient> getHighestBalanceAccountsPerClient() {
        return reportRepository.findHighestBalanceAccountsPerClient();
    }

}
