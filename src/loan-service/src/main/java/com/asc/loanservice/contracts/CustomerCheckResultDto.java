package com.asc.loanservice.contracts;


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
}
