package com.asc.loanservice.domain.loan.rules;

import com.asc.loanservice.contracts.LoanRequestDto;
import com.asc.loanservice.domain.parametrization.ParametrizationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerIncomeRuleTest {

    @Mock
    private ParametrizationService parametrizationService;

    @InjectMocks
    private CustomerIncomeRule customerIncomeRule;

    @Before
    public void setup() {
        when(parametrizationService.getLoanInterest()).thenReturn(new BigDecimal("0.04"));
    }

    @Test
    public void shouldAcceptLoanWhenIncomeIsGreaterThan15Percent() {

        // given
        LoanRequestDto loanRequestDto = new LoanRequestDto();
        loanRequestDto.setCustomerMonthlyIncome(new BigDecimal(11000));
        loanRequestDto.setLoanAmount(new BigDecimal(1500)); //plus bank interest
        loanRequestDto.setNumberOfInstallments(1);

        // when
        boolean isValid = customerIncomeRule.check(loanRequestDto);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void shouldRejectLoanWhenIncomeIsTooLow() {

        // given
        LoanRequestDto loanRequestDto = new LoanRequestDto();
        loanRequestDto.setCustomerMonthlyIncome(new BigDecimal(10000));
        loanRequestDto.setLoanAmount(new BigDecimal(1500)); //plus bank interest
        loanRequestDto.setNumberOfInstallments(1);

        // when
        boolean isValid = customerIncomeRule.check(loanRequestDto);

        // then
        assertThat(isValid).isFalse();
    }
}
