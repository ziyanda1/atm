package za.co.discovery.atm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.stereotype.Service;
import za.co.discovery.atm.domain.ATM;
import za.co.discovery.atm.domain.Client;
import za.co.discovery.atm.domain.LoginRequest;
import za.co.discovery.atm.repository.ATMRepository;
import za.co.discovery.atm.repository.ClientRepository;
import za.co.discovery.atm.service.ATMService;
import za.co.discovery.atm.service.ClientService;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client getByClientId(Integer clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if(!client.isPresent()){
            throw new RuntimeException("Could not find any data linked to card");
        }
        return client.get();
    }

}
