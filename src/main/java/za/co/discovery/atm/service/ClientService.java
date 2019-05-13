package za.co.discovery.atm.service;

import za.co.discovery.atm.domain.ATM;
import za.co.discovery.atm.domain.Client;

public interface ClientService {
    Client getByClientId(Integer clientId);
}
