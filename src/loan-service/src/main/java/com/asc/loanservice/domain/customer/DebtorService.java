package com.asc.loanservice.domain.customer;

import com.asc.loanservice.contracts.CustomerCheckResultDto;
import com.asc.loanservice.integration.DebtorIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DebtorService {

    private final DebtorIntegrationService debtorIntegrationService;

    public boolean isRegisteredDebtor(String customerTaxId) {
        boolean isRegisteredDebtor = Optional.ofNullable(debtorIntegrationService.checkCustomer(customerTaxId))
                .map(CustomerCheckResultDto::isRegisteredDebtor)
                .orElse(false);

        return isRegisteredDebtor;
    }
}
