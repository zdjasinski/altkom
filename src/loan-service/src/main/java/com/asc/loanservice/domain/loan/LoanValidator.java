package com.asc.loanservice.domain.loan;

import com.asc.loanservice.contracts.LoanRequestDto;
import com.asc.loanservice.domain.loan.rules.LoanRule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoanValidator {

    private final List<LoanRule> loanRules;

    public boolean validate(LoanRequestDto loanRequestData) {
        return loanRules.stream().allMatch(loanRule -> loanRule.check(loanRequestData));
    }
}
