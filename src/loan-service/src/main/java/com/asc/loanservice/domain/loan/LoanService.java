package com.asc.loanservice.domain.loan;

import com.asc.loanservice.contracts.LoanRequestDataDto;
import com.asc.loanservice.contracts.LoanRequestDto;
import com.asc.loanservice.contracts.LoanRequestEvaluationResult;
import com.asc.loanservice.contracts.LoanRequestRegistrationResultDto;
import com.asc.loanservice.mappers.LoanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoanService {

    private final LoanValidator loanValidator;
    private final LoanRepository loanRepository;

    public LoanRequestRegistrationResultDto register(LoanRequestDto loanRequestDto) {
        LoanRequestEvaluationResult status = loanValidator.validate(loanRequestDto) ?
                LoanRequestEvaluationResult.APPROVED :
                LoanRequestEvaluationResult.REJECTED;

        Loan newLoan = loanRepository.addLoan(prepareLoanToSave(loanRequestDto, status));

        return new LoanRequestRegistrationResultDto(
            newLoan.getLoanRequestNumber(),
            newLoan.getEvaluationResult()
        );
    }

    public LoanRequestDataDto getByNumber(String loanNumber) {
        Loan loan = loanRepository.getByNumber(loanNumber);

        return LoanMapper.loanToLoanRequestDataDto(loan);
    }

    private Loan prepareLoanToSave(LoanRequestDto loanRequestDto, LoanRequestEvaluationResult status) {
        Loan loan = LoanMapper.loanRequestDtoToLoan(loanRequestDto);
        loan.setLoanRequestNumber(UUID.randomUUID().toString());
        loan.setEvaluationResult(status);
        loan.setRegistrationDate(LocalDateTime.now());

        return loan;
    }
}
