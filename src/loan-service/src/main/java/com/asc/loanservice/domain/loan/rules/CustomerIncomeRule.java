package com.asc.loanservice.domain.loan.rules;

import com.asc.loanservice.contracts.LoanRequestDto;
import com.asc.loanservice.domain.parametrization.ParametrizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerIncomeRule implements LoanRule {

    private final static BigDecimal MONTHS_IN_YEAR = new BigDecimal(12);
    private final static BigDecimal BASE = new BigDecimal("0.15");

    private final ParametrizationService parametrizationService;

    @Override
    public boolean check(LoanRequestDto loanRequestDto) {
        Assert.notNull(loanRequestDto.getCustomerMonthlyIncome(), "customerMonthlyIncome argument must not be null!");
        Assert.notNull(loanRequestDto.getLoanAmount(), "loanAmount argument must not be null!");
        Assert.notNull(loanRequestDto.getNumberOfInstallments(), "numberOfInstallments argument must not be null!");

        BigDecimal installment = calculateInstallment(loanRequestDto.getLoanAmount(), BigDecimal.valueOf(loanRequestDto.getNumberOfInstallments()));
        BigDecimal income = loanRequestDto.getCustomerMonthlyIncome();

        return income.multiply(BASE).compareTo(installment) > 0;
    }

    private BigDecimal calculateInstallment(BigDecimal loanAmount, BigDecimal numberOfInstallments) {
        BigDecimal interest = parametrizationService.getLoanInterest();
        BigDecimal annualInterestRate = interest.multiply(loanAmount).divide(MONTHS_IN_YEAR, BigDecimal.ROUND_CEILING);

        return loanAmount.divide(numberOfInstallments, BigDecimal.ROUND_CEILING).add(annualInterestRate);
    }
}
