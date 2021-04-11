package com.asc.loanservice.integration;

import com.asc.loanservice.contracts.CustomerCheckResultDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DebtorIntegrationServiceTest {

    @Mock
    private DebtorIntegrationService debtorIntegrationService;

    @Test
    public void shouldCheckCustomerTaxId() {

        // given
        String customerTaxId = "eca3c7b5-ed10-4048-8b7b-744c600ef5e9";
        CustomerCheckResultDto customerCheckResultDto = new CustomerCheckResultDto(customerTaxId, true);
        when(debtorIntegrationService.checkCustomer(customerTaxId)).thenReturn(customerCheckResultDto);

        // when
        CustomerCheckResultDto resultDto = debtorIntegrationService.checkCustomer(customerTaxId);

        // then
        Assert.assertEquals(resultDto.getCustomerTaxId(), customerTaxId);
    }

    @Test
    public void shouldCheckCustomerIsRegisteredAsDebtor() {

        // given
        String customerTaxId = "12345";
        CustomerCheckResultDto customerCheckResultDto = new CustomerCheckResultDto(customerTaxId, true);
        when(debtorIntegrationService.checkCustomer(customerTaxId)).thenReturn(customerCheckResultDto);

        // when
        CustomerCheckResultDto resultDto = debtorIntegrationService.checkCustomer(customerTaxId);

        // then
        Assert.assertTrue(resultDto.isRegisteredDebtor());
    }

    @Test
    public void shouldCheckCustomerIsNotRegisteredAsDebtor() {

        // given
        String customerTaxId = "12345";
        CustomerCheckResultDto customerCheckResultDto = new CustomerCheckResultDto(customerTaxId, false);
        when(debtorIntegrationService.checkCustomer(customerTaxId)).thenReturn(customerCheckResultDto);

        // when
        CustomerCheckResultDto resultDto = debtorIntegrationService.checkCustomer(customerTaxId);

        // then
        Assert.assertFalse(resultDto.isRegisteredDebtor());
    }
}
