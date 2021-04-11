package com.asc.loanservice.domain.loan.rules;

import com.asc.loanservice.contracts.LoanRequestDto;

public interface LoanRule {
    boolean check(LoanRequestDto loanRequestDto);
}
