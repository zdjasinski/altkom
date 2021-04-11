package com.asc.debtorregistry.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCheckResultDto {
    private String customerTaxId;
    private boolean isRegisteredDebtor;

    public static CustomerCheckResultDto debtorCustomer(String customerTaxId){
        return new CustomerCheckResultDto(customerTaxId, true);
    }

    public static CustomerCheckResultDto notDebtorCustomer(String customerTaxId){
        return new CustomerCheckResultDto(customerTaxId, false);
    }
}
