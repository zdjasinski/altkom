package com.asc.loanservice.domain.loan.rules;

import com.asc.loanservice.contracts.LoanRequestDto;
import com.asc.loanservice.domain.customer.DebtorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerIsDebtorRule implements LoanRule {

    private final DebtorService debtorService;

    @Override
    public boolean check(LoanRequestDto loanRequestDto) {
        Assert.notNull(loanRequestDto.getCustomerTaxId(), "CustomerTaxId argument must not be null!");

        boolean isRegisteredDebtor = debtorService.isRegisteredDebtor(loanRequestDto.getCustomerTaxId());

        return !isRegisteredDebtor;
    }
}
