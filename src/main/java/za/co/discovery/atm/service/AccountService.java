package za.co.discovery.atm.service;

import za.co.discovery.atm.domain.ClientAccount;
import za.co.discovery.atm.web.dto.HighestBalanceAccountPerClient;
import za.co.discovery.atm.web.dto.WithdrawFundsRequestDto;
import za.co.discovery.atm.web.dto.WithdrawFundsResponseDto;

import java.util.List;

public interface AccountService {
    List<ClientAccount> getTransactionalAccounts(Integer clientId);
    List<ClientAccount> getCurrencyAccounts(Integer clientId);
    List<WithdrawFundsResponseDto> withdrawFunds(WithdrawFundsRequestDto withdrawFundsRequestDto);
    List<HighestBalanceAccountPerClient> findHighestBalanceAccountsPerClient();
}
