package com.asc.loanservice.domain.loan.rules;

import com.asc.loanservice.contracts.LoanRequestDto;
import com.asc.loanservice.domain.customer.DebtorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerIsDebtorRuleTest {

    private final String customerTaxId = "eca3c7b5-ed10-4048-8b7b-744c600ef5e9";

    @Mock
    private DebtorService debtorService;

    @InjectMocks
    private CustomerIsDebtorRule customerIsDebtorRule;

    @Test
    public void shouldReturnFalseWhenCustomerIsRegisteredAsDebtor() {

        // given
        LoanRequestDto loanRequestDto = new LoanRequestDto();
        loanRequestDto.setCustomerTaxId(customerTaxId);
        when(debtorService.isRegisteredDebtor(customerTaxId)).thenReturn(true);

        // when
        boolean isDebtor = customerIsDebtorRule.check(loanRequestDto);

        // then
        assertThat(isDebtor).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenCustomerIsNotRegisteredAsDebtor() {

        // given
        LoanRequestDto loanRequestDto = new LoanRequestDto();
        loanRequestDto.setCustomerTaxId(customerTaxId);
        when(debtorService.isRegisteredDebtor(customerTaxId)).thenReturn(false);

        // when
        boolean isDebtor = customerIsDebtorRule.check(loanRequestDto);

        // then
        assertThat(isDebtor).isTrue();
    }
}
