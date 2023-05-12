package com.visma.loan.calculation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visma.loan.calculation.entity.HousingLoan;
import com.visma.loan.calculation.response.CalculateResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCalculateLoanPayment() throws Exception {
        HousingLoan loan = new HousingLoan(10,BigDecimal.valueOf(10000));

        ObjectMapper objectMapper = new ObjectMapper();
        String loanJson = objectMapper.writeValueAsString(loan);

        MvcResult result = mockMvc.perform(post("/loan/calculateLoan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loanJson))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        CalculateResponse calculateResponse = objectMapper.readValue(responseJson, CalculateResponse.class);
        assertEquals(new BigDecimal("98.89"), calculateResponse.getMonthlyPayment());
    }
    @Test
    public void testCalculateLoanPaymentException400() throws Exception {
        HousingLoan loan = new HousingLoan(10,BigDecimal.valueOf(10000));

        ObjectMapper objectMapper = new ObjectMapper();
        String loanJson = objectMapper.writeValueAsString(loan);
        loanJson = loanJson.replace("loanPeriodYear","term");

        MvcResult result = mockMvc.perform(post("/loan/calculateLoan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loanJson))
                .andExpect(status().isBadRequest())
                .andReturn();

    }
}