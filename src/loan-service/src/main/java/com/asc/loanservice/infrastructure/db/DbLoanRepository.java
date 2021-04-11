package com.asc.loanservice.infrastructure.db;

import com.asc.loanservice.domain.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbLoanRepository extends JpaRepository<Loan, String> {
}
