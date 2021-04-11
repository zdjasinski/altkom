package com.asc.loanservice.domain.loan.rules;

import com.asc.loanservice.contracts.LoanRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.Period;

@Component
public class CustomerAgeRule implements LoanRule {

    private final static int MAX_AGE = 65;
    private final static float MONTHS_IN_YEAR = 12.0f;

    @Override
    public boolean check(LoanRequestDto loanRequestDto) {
        Assert.notNull(loanRequestDto.getCustomerBirthday(), "customerBirthday argument must not be null!");
        Assert.notNull(loanRequestDto.getFirstInstallmentDate(), "firstInstallmentDate argument must not be null!");
        Assert.notNull(loanRequestDto.getNumberOfInstallments(), "numberOfInstallments argument must not be null!");

        LocalDate birthDate = loanRequestDto.getCustomerBirthday();
        LocalDate firstInstallmentDate = loanRequestDto.getFirstInstallmentDate();
        LocalDate lastInstallmentDate = firstInstallmentDate.plusMonths(loanRequestDto.getNumberOfInstallments());

        float totalMonths = Period.between(birthDate, lastInstallmentDate).toTotalMonths();
        double yearsRoundedToUp = Math.ceil(totalMonths / MONTHS_IN_YEAR);

        return yearsRoundedToUp <= MAX_AGE;
    }
}
