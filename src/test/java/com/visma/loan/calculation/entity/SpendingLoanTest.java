package com.visma.loan.calculation.entity;

import com.visma.loan.calculation.response.CalculateResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class SpendingLoanTest {

    @Test
    public void testValidMonthlyPayment() {
        int term = 20;
        BigDecimal amount = new BigDecimal("200000");
        BigDecimal expectedMonthlyPayment = new BigDecimal("1030.82");
        SpendingLoan spendingLoan = new SpendingLoan(term, amount);
        CalculateResponse calculateResponse = spendingLoan.calculatePayment(spendingLoan);
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
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

