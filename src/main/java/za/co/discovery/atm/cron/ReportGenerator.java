package za.co.discovery.atm.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import za.co.discovery.atm.service.ReportService;
import za.co.discovery.atm.util.MailUtil;

@Component
@EnableScheduling
public class ReportGenerator {

   private static final Logger LOG = LoggerFactory.getLogger(ReportGenerator.class);

    @Autowired
    private ReportService reportService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void sendTransactionalAccountPerClientWithTheHighestBalance() {
       try {
           MailUtil.sendmail(reportService.getHighestBalanceAccountsPerClient().toString());
       }catch (Exception ex){
           LOG.error("An error occured while sending transactional account per client with the highest balance.", ex);
       }
    }
}
