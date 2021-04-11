package com.asc.loanservice.domain.loan;

import com.asc.loanservice.contracts.LoanRequestDataDto;
import com.asc.loanservice.contracts.LoanRequestDto;
import com.asc.loanservice.contracts.LoanRequestEvaluationResult;
import com.asc.loanservice.contracts.LoanRequestRegistrationResultDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {

    @Mock
    private LoanValidator loanValidator;

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    @Test
    public void shouldRegisterRejectedLoan() {
        // given
        String requestNumber = "12345";
        LoanRequestEvaluationResult evaluationResult = LoanRequestEvaluationResult.REJECTED;

        Loan createdLoan = new Loan();
        createdLoan.setLoanRequestNumber(requestNumber);
        createdLoan.setEvaluationResult(evaluationResult);

        when(loanValidator.validate(any(LoanRequestDto.class))).thenReturn(true);
        when(loanRepository.addLoan(any(Loan.class))).thenReturn(createdLoan);

        // when
        LoanRequestRegistrationResultDto result = loanService.register(new LoanRequestDto());

        // then
        assertThat(result.getLoanRequestNumber()).isEqualTo(requestNumber);
        assertThat(result.getEvaluationResult()).isEqualTo(createdLoan.getEvaluationResult());
    }

    @Test
    public void shouldRegisterAcceptedLoan() {
        // given
        String requestNumber = "54321";
        LoanRequestEvaluationResult evaluationResult = LoanRequestEvaluationResult.APPROVED;

        Loan createdLoan = new Loan();
        createdLoan.setLoanRequestNumber(requestNumber);
        createdLoan.setEvaluationResult(evaluationResult);

        when(loanValidator.validate(any(LoanRequestDto.class))).thenReturn(true);
        when(loanRepository.addLoan(any(Loan.class))).thenReturn(createdLoan);

        // when
        LoanRequestRegistrationResultDto result = loanService.register(new LoanRequestDto());

        // then
        assertThat(result.getLoanRequestNumber()).isEqualTo(requestNumber);
        assertThat(result.getEvaluationResult()).isEqualTo(createdLoan.getEvaluationResult());
    }

    @Test
    public void shouldReturnRequestedLoan() {
        // given
        String requestNumber = "111-222-333";
        String customerName = "James Gosling";
        LocalDate customerBirthday = LocalDate.of(1955, Month.MAY, 19);
        String customerTaxId = "12345";
        BigDecimal customerMonthlyIncome = new BigDecimal(10_000);
        BigDecimal loanAmount = new BigDecimal(100_000);
        Integer numberOfInstallments = 12 * 10;
        LocalDate firstInstallmentDate = LocalDate.of(2020, Month.JANUARY, 15);
        LoanRequestEvaluationResult evaluationResult = LoanRequestEvaluationResult.REJECTED;
        LocalDateTime registrationDate = LocalDateTime.of(2021, Month.APRIL, 11, 23, 59);
        Loan loan = new Loan(
                requestNumber,
                customerName,
                customerBirthday,
                customerTaxId,
                customerMonthlyIncome,
                loanAmount,
                numberOfInstallments,
                firstInstallmentDate,
                evaluationResult,
                registrationDate
        );
        when(loanRepository.getByNumber(any(String.class))).thenReturn(loan);

        // when
        LoanRequestDataDto result = loanService.getByNumber(requestNumber);

        // then
        assertThat(result.getLoanRequestNumber()).isEqualTo(requestNumber);
        assertThat(result.getCustomerName()).isEqualTo(customerName);
        assertThat(result.getCustomerBirthday()).isEqualTo(customerBirthday);
        assertThat(result.getCustomerTaxId()).isEqualTo(customerTaxId);
        assertThat(result.getCustomerMonthlyIncome()).isEqualTo(customerMonthlyIncome);
        assertThat(result.getLoanAmount()).isEqualTo(loanAmount);
        assertThat(result.getNumberOfInstallments()).isEqualTo(numberOfInstallments);
        assertThat(result.getFirstInstallmentDate()).isEqualTo(firstInstallmentDate);
        assertThat(result.getEvaluationResult()).isEqualTo(evaluationResult);
        assertThat(result.getRegistrationDate()).isEqualTo(registrationDate);
    }
}
