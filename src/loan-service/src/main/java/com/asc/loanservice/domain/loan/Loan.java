package com.asc.loanservice.domain.loan;

import com.asc.loanservice.contracts.LoanRequestEvaluationResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    @Id
    @Column(updatable = false, nullable = false)
    private String loanRequestNumber;
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
    @NotNull
    private LoanRequestEvaluationResult evaluationResult;
    @NotNull
    private LocalDateTime registrationDate;
}
