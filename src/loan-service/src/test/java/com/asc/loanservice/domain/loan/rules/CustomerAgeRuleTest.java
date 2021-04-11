package com.asc.loanservice.domain.loan.rules;

import com.asc.loanservice.contracts.LoanRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CustomerAgeRuleTest {

    private final CustomerAgeRule customerAgeRule = new CustomerAgeRule();

    @Test
    public void shouldAcceptLoanWhenCustomerAgeWillBe64() {

        // given
        LoanRequestDto loanRequestDto = new LoanRequestDto();
        loanRequestDto.setCustomerBirthday(LocalDate.of(2000, Month.JANUARY, 1));
        loanRequestDto.setFirstInstallmentDate(LocalDate.of(2030, Month.JANUARY, 15));
        loanRequestDto.setNumberOfInstallments(12 * 35 - 1); //64 years, 11 month

        // when
        boolean isValid = customerAgeRule.check(loanRequestDto);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void shouldAcceptLoanWhenCustomerAgeWillBeEqual65() {

        // given
        LoanRequestDto loanRequestDto = new LoanRequestDto();
        loanRequestDto.setCustomerBirthday(LocalDate.of(2000, Month.JANUARY, 1));
        loanRequestDto.setFirstInstallmentDate(LocalDate.of(2030, Month.JANUARY, 15));
        loanRequestDto.setNumberOfInstallments(12 * 35); //65 years

        // when
        boolean isValid = customerAgeRule.check(loanRequestDto);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void shouldRejectLoanWhenCustomerAgeWillBeGreaterThan65() {

        // given
        LoanRequestDto loanRequestDto = new LoanRequestDto();
        loanRequestDto.setCustomerBirthday(LocalDate.of(2000, Month.JANUARY, 1));
        loanRequestDto.setFirstInstallmentDate(LocalDate.of(2030, Month.JANUARY, 15));
        loanRequestDto.setNumberOfInstallments(12 * 35 + 1); //65 years, 1 month

        // when
        boolean isValid = customerAgeRule.check(loanRequestDto);

        // then
        assertThat(isValid).isFalse();
    }
}
