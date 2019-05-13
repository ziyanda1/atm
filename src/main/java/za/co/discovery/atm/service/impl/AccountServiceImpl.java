package za.co.discovery.atm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery.atm.domain.*;
import za.co.discovery.atm.repository.*;
import za.co.discovery.atm.service.AccountService;
import za.co.discovery.atm.web.dto.HighestBalanceAccountPerClient;
import za.co.discovery.atm.web.dto.WithdrawFundsRequestDto;
import za.co.discovery.atm.web.dto.WithdrawFundsResponseDto;

import java.math.BigDecimal;
import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private ClientAccountRepository clientAccountRepository;

    @Autowired
    private CurrencyConversionRepository currencyConversionRepository;

    @Autowired
    private ATMAllocationRepository atmAllocationRepository;

    @Autowired
    private ATMRepository atmRepository;

    @Autowired
    ReportRepository reportRepository;

    @Override
    public List<ClientAccount> getTransactionalAccounts(Integer clientId) {
        Optional<List<ClientAccount>> clientAccounts = clientAccountRepository.findAccountsByClientId(clientId, true);
        if(!clientAccounts.isPresent()){
            throw new RuntimeException("Could not find any transactional accounts linked to client");
        }
        return clientAccounts.get();
    }

    @Override
    public List<ClientAccount> getCurrencyAccounts(Integer clientId) {
        Optional<List<ClientAccount>> clientAccountsPromise = clientAccountRepository.findAccountsByClientId(clientId, false);
        if(!clientAccountsPromise.isPresent()) {
            throw new RuntimeException("Could not find any currency accounts linked to client");
        }
        List<ClientAccount> clientAccounts = clientAccountsPromise.get();
        for(ClientAccount clientAccount: clientAccounts) {
            CurrencyConversionRate currencyConversionRate = currencyConversionRepository.findByCurrencyCode(clientAccount.getCurrency().getCurrencyCode());
            clientAccount.getCurrency().setCurrencyConversionRate(currencyConversionRate);
        }
        return clientAccounts;
    }

    @Override
    public List<WithdrawFundsResponseDto> withdrawFunds(WithdrawFundsRequestDto withdrawFundsRequestDto) {

        Optional<ClientAccount> clientAccountPromise = clientAccountRepository.findById(withdrawFundsRequestDto.getAccountNumber());

        if(!clientAccountPromise.isPresent()){
            throw new RuntimeException("No client account present");
        }

        ClientAccount clientAccount = clientAccountPromise.get();
        ATM atm = atmRepository.findById(withdrawFundsRequestDto.getAtmId()).get();
        List<ATMAllocation> atmAllocations = atmAllocationRepository.findByAtm(atm);

        if(atmAllocations == null || atmAllocations.isEmpty()) {
            throw new RuntimeException("ATM has no funds.");
        }

        List<WithdrawFundsResponseDto> withdrawFundsResponsDtos = new ArrayList<>();

        BigDecimal amount = withdrawFundsRequestDto.getAmount();

        for(ATMAllocation allocation: atmAllocations) {

            if (amount.compareTo(allocation.getDenomination().getValue()) > 0){
                WithdrawFundsResponseDto withdrawFundsResponseDto = new WithdrawFundsResponseDto();
                withdrawFundsResponseDto.setDenomination(allocation.getDenomination());
                withdrawFundsResponseDto.setCount(amount.divide(allocation.getDenomination().getValue()).intValue());
                amount = amount.subtract(allocation.getDenomination().getValue());
                allocation.setCount(allocation.getCount() - withdrawFundsResponseDto.getCount());
                withdrawFundsResponsDtos.add(withdrawFundsResponseDto);
                atmAllocationRepository.saveAndFlush(allocation);
            }
        }

        if(clientAccount.getAccountType().isTransactional()){

            if ("CHEQUE".equals(clientAccount.getAccountType().getAccountTypeCode())){
                if(clientAccount.getDisplayBalance().compareTo(BigDecimal.ZERO) < 0){
                    if(clientAccount.getDisplayBalance().abs().add(withdrawFundsRequestDto.getAmount()).compareTo(new BigDecimal(10000.00)) > 0){
                        throw new RuntimeException("You have reached your negative balance limit.");
                    } else {
                        clientAccount.setDisplayBalance(clientAccount.getDisplayBalance().subtract(withdrawFundsRequestDto.getAmount()));
                        clientAccountRepository.saveAndFlush(clientAccount);
                    }
                } else {
                    clientAccount.setDisplayBalance(clientAccount.getDisplayBalance().subtract(withdrawFundsRequestDto.getAmount()));
                    clientAccountRepository.saveAndFlush(clientAccount);
                }
            } else {
                if(clientAccount.getDisplayBalance().compareTo(BigDecimal.ZERO) > 0){
                    if(clientAccount.getDisplayBalance().subtract(withdrawFundsRequestDto.getAmount()).compareTo(BigDecimal.ZERO) > 0){
                        clientAccount.setDisplayBalance(clientAccount.getDisplayBalance().subtract(withdrawFundsRequestDto.getAmount()));
                        clientAccountRepository.saveAndFlush(clientAccount);
                    } else {
                        throw new RuntimeException("Requested fund is greater than available funds.");
                    }
                }else {
                    throw new RuntimeException("You have no available funds in your account.");
                }

            }
        }

        return withdrawFundsResponsDtos;
    }

    @Override
    public List<HighestBalanceAccountPerClient> findHighestBalanceAccountsPerClient() {
        return reportRepository.findHighestBalanceAccountsPerClient();
    }
}
