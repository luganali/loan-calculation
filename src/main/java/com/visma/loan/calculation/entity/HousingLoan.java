package com.visma.loan.calculation.entity;

import com.visma.loan.calculation.entity.base.Loan;
import com.visma.loan.calculation.entity.base.Type;
import com.visma.loan.calculation.response.CalculateResponse;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class HousingLoan extends Loan<HousingLoan> {
    public HousingLoan(int term, BigDecimal amount) {
        super(Type.HOUSING_LOAN, BigDecimal.valueOf(0.035), term, amount);
    }

    @Override
    public CalculateResponse calculatePayment(HousingLoan loan) {
        BigDecimal monthlyInterestRate = loan.getInterestRate().divide(BigDecimal.valueOf(12), MathContext.DECIMAL128);
        int numberOfPayments = loan.getTerm() * 12;
        BigDecimal numerator = loan.getLoanAmount().multiply(monthlyInterestRate).multiply(BigDecimal.ONE.add(monthlyInterestRate).pow(numberOfPayments));
        BigDecimal denominator = BigDecimal.ONE.add(monthlyInterestRate).pow(numberOfPayments).subtract(BigDecimal.ONE);
        BigDecimal monthlyPayment = numerator.divide(denominator, MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_UP);
        return CalculateResponse.builder().monthlyPayment(monthlyPayment).build();
    }

}
