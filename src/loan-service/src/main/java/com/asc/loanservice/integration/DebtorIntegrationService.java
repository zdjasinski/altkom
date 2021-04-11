package com.asc.loanservice.integration;

import com.asc.loanservice.contracts.CustomerCheckResultDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class DebtorIntegrationService {

    private static final String API_URL = "/api/customercheck";

    @Value("${services.debtor.url}")
    private String debtorServiceUrl;

    public CustomerCheckResultDto checkCustomer(String customerTaxId) {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(debtorServiceUrl + API_URL + "/" + customerTaxId);

        HttpEntity<CustomerCheckResultDto> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                CustomerCheckResultDto.class);

        return response.getBody();
    }
}
