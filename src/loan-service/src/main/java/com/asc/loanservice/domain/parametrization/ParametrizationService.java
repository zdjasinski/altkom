package com.asc.loanservice.domain.parametrization;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ParametrizationService {

    public BigDecimal getLoanInterest() {
        //TODO e.g fetch data from DB
        return new BigDecimal("0.04");
    }
}
