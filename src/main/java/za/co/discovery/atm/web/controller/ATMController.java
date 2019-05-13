package za.co.discovery.atm.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.atm.domain.ATM;
import za.co.discovery.atm.domain.Client;
import za.co.discovery.atm.domain.ClientAccount;
import za.co.discovery.atm.domain.LoginRequest;
import za.co.discovery.atm.service.ATMService;
import za.co.discovery.atm.service.AccountService;
import za.co.discovery.atm.service.ClientService;
import za.co.discovery.atm.util.ATMUtil;
import za.co.discovery.atm.web.dto.WithdrawFundsRequestDto;
import za.co.discovery.atm.web.dto.WithdrawFundsResponseDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/ATM")
public class ATMController {

    @Autowired
    private ATMService atmService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/allocate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "initialises a random Automatic Teller Machine")
    public ATM getAtm(HttpServletResponse httpServletResponse) {
        ATM atm = null;
        try {
            atm = atmService.allocateATM();
            ATMUtil.setSuccessHeaders(httpServletResponse);
        } catch (Exception ex){
            ATMUtil.setErrorHeaders(httpServletResponse, ex.getMessage());
        }
        return atm;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Logs in a random client")
    public Client login(@ApiParam @RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        Client client = null;
        try {
            Integer clientId = getClientId(loginRequest);
            client = clientService.getByClientId(clientId);
            ATMUtil.setSuccessHeaders(httpServletResponse);
        }catch (Exception ex){
            ATMUtil.setErrorHeaders(httpServletResponse, ex.getMessage());
        }
        return client;
    }

    @GetMapping(value = "account/transactional/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gets all transactional accounts linked to client")
    public List<ClientAccount> getTransactionalAccounts(@PathVariable Integer clientId, HttpServletResponse httpServletResponse){
        List<ClientAccount> transactionalAccounts = null;
        try {
            transactionalAccounts = accountService.getTransactionalAccounts(clientId);
            ATMUtil.setSuccessHeaders(httpServletResponse);
        }catch (Exception ex){
            ATMUtil.setErrorHeaders(httpServletResponse, ex.getMessage());
        }
        return transactionalAccounts;
    }

    @GetMapping(value = "account/currency/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gets all currency accounts linked to client")
    public  List<ClientAccount>  getCurrencyAccounts(@PathVariable Integer clientId, HttpServletResponse httpServletResponse) {
        List<ClientAccount> currencyAccounts = null;
        try {
            currencyAccounts = accountService.getCurrencyAccounts(clientId);
            ATMUtil.setSuccessHeaders(httpServletResponse);
        }catch (Exception ex){
            ATMUtil.setErrorHeaders(httpServletResponse, ex.getMessage());
        }
        return currencyAccounts;
    }

    @PostMapping(value = "account/withdraw", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Withdraw funds")
    public List<WithdrawFundsResponseDto> withDrawFunds(@ApiParam  @RequestBody WithdrawFundsRequestDto withdrawFundsRequestDto, HttpServletResponse httpServletResponse){
        List<WithdrawFundsResponseDto> withdrawFundsResponseDto = null;
        try {
            withdrawFundsResponseDto = accountService.withdrawFunds(withdrawFundsRequestDto);
            ATMUtil.setSuccessHeaders(httpServletResponse);
        }catch (Exception ex){
            ATMUtil.setErrorHeaders(httpServletResponse, ex.getMessage());
        }
        return withdrawFundsResponseDto;
    }

    private Integer getClientId(LoginRequest loginRequest) {
        return (loginRequest.getCardProfile() != null && loginRequest.getCardProfile().getPin() != null
                && loginRequest.getCardProfile().getNumber() != null) ? 1 : 0;
    }

}
