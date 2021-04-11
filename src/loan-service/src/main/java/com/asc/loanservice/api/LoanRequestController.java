package com.asc.loanservice.api;

import com.asc.loanservice.contracts.LoanRequestDataDto;
import com.asc.loanservice.contracts.LoanRequestDto;
import com.asc.loanservice.contracts.LoanRequestRegistrationResultDto;
import com.asc.loanservice.domain.loan.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoanRequestController {

    private final LoanService loanService;

    @PostMapping
    public LoanRequestRegistrationResultDto register(@RequestBody @Valid LoanRequestDto loanRequest){
        LoanRequestRegistrationResultDto result = loanService.register(loanRequest);
        return new LoanRequestRegistrationResultDto(result.getLoanRequestNumber(), result.getEvaluationResult());
    }

    @GetMapping("/{loanNumber}")
    public LoanRequestDataDto getByNumber(@PathVariable("loanNumber") String loanNumber){
        return loanService.getByNumber(loanNumber);
    }
}
