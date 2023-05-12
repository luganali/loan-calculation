package com.visma.loan.calculation.entity;

import com.visma.loan.calculation.response.CalculateResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import java.math.BigDecimal;

public class HousingLoanTest {

    @Test
    public void testValidMonthlyPayment() {
        int term = 20;
        BigDecimal amount = new BigDecimal("200000");
        BigDecimal expectedMonthlyPayment = new BigDecimal("1159.92");
        HousingLoan housingLoan = new HousingLoan(term, amount);
        CalculateResponse calculateResponse = housingLoan.calculatePayment(housingLoan);
        Assertions.assertEquals(expectedMonthlyPayment, calculateResponse.getMonthlyPayment());
    }

    @Test
    public void testZeroLoanAmount() {
        int term = 10;
        BigDecimal amount = new BigDecimal("0");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            HousingLoan housingLoan = new HousingLoan(term, amount);
            housingLoan.calculatePayment(housingLoan);
        }, "Loan amount cannot be zero.");
    }

    @Test
    public void testNegativeLoanTerm() {
        int term = -5;
        BigDecimal amount = new BigDecimal("100000");
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            HousingLoan housingLoan = new HousingLoan(term, amount);
            housingLoan.calculatePayment(housingLoan);
        }, "Loan term must be positive.");
    }

    @Test
    public void testZeroLoanTerm() {
        int term = 0;
        BigDecimal amount = new BigDecimal("100000");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            HousingLoan housingLoan = new HousingLoan(term, amount);
            housingLoan.calculatePayment(housingLoan);
        }, "Loan term cannot be zero.");
    }

}

