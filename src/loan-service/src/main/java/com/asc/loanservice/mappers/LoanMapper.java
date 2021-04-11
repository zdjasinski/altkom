package com.asc.loanservice.mappers;

import com.asc.loanservice.contracts.LoanRequestDataDto;
import com.asc.loanservice.contracts.LoanRequestDto;
import com.asc.loanservice.domain.loan.Loan;

public class LoanMapper extends AbstractMapper {

    public static Loan loanRequestDtoToLoan(final LoanRequestDto dto) {
        return modelMapper.map(dto, Loan.class);
    }

    public static LoanRequestDataDto loanToLoanRequestDataDto(final Loan dbo) {
        return modelMapper.map(dbo, LoanRequestDataDto.class);
    }
}
