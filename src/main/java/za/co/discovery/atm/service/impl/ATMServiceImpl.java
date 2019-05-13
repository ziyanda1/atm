package za.co.discovery.atm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery.atm.domain.ATM;
import za.co.discovery.atm.repository.ATMRepository;
import za.co.discovery.atm.service.ATMService;

import java.util.Optional;

@Service
public class ATMServiceImpl implements ATMService {

    @Autowired
    private ATMRepository atmRepository;

    @Override
    public ATM allocateATM() {
        Optional<ATM> atm =  atmRepository.findAll().stream().findAny();
        if(!atm.isPresent()) {
            throw new RuntimeException();
        }
        return atm.get();
    }
}
