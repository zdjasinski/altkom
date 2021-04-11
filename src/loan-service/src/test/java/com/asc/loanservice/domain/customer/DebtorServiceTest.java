package com.asc.loanservice.domain.customer;

import com.asc.loanservice.contracts.CustomerCheckResultDto;
import com.asc.loanservice.integration.DebtorIntegrationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DebtorServiceTest {

    private final String customerTaxId = "12345";

    @Mock
    private DebtorIntegrationService debtorIntegrationService;

    @InjectMocks
    private DebtorService debtorService;


    @Test
    public void shouldCheckCustomerIsRegisteredAsDebtor() {

        // given
        CustomerCheckResultDto customerCheckResultDto = new CustomerCheckResultDto(customerTaxId, true);
        when(debtorIntegrationService.checkCustomer(customerTaxId)).thenReturn(customerCheckResultDto);

        // when
        boolean registeredDebtor = debtorService.isRegisteredDebtor(customerTaxId);

        // then
        Assert.assertTrue(registeredDebtor);
    }

    @Test
    public void shouldCheckCustomerIsNotRegisteredAsDebtor() {

        // given
        CustomerCheckResultDto customerCheckResultDto = new CustomerCheckResultDto(customerTaxId, false);
        when(debtorIntegrationService.checkCustomer(customerTaxId)).thenReturn(customerCheckResultDto);

        // when
        boolean registeredDebtor = debtorService.isRegisteredDebtor(customerTaxId);

        // then
        Assert.assertFalse(registeredDebtor);
    }
}
