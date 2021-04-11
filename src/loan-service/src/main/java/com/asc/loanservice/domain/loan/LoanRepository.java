package com.asc.loanservice.domain.loan;

import com.asc.loanservice.infrastructure.db.DbLoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoanRepository {

    private final DbLoanRepository dbLoanRepository;

    public Loan addLoan(Loan loan) {
        return dbLoanRepository.save(loan);
    }

    public Loan getByNumber(String loanRequestNumber) {
        return dbLoanRepository.getOne(loanRequestNumber);
    }
}
