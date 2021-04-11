package com.asc.loanservice.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequestDto {
    @NotBlank
    private String customerName;
    @NotNull
    @Past
    private LocalDate customerBirthday;
    @NotBlank
    private String customerTaxId;
    @NotNull
    private BigDecimal customerMonthlyIncome;
    @NotNull
    @Positive
    private BigDecimal loanAmount;
    @NotNull
    @Positive
    private Integer numberOfInstallments;
    @NotNull
    @Future
    private LocalDate firstInstallmentDate;
}
