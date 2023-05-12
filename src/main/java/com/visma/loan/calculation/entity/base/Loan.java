package com.visma.loan.calculation.entity.base;

import com.visma.loan.calculation.entity.HousingLoan;
import com.visma.loan.calculation.entity.SpendingLoan;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.visma.loan.calculation.response.CalculateResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = HousingLoan.class, name = Type.HOUSING_LOAN),
        @JsonSubTypes.Type(value = SpendingLoan.class, name = Type.SPENDING_LOAN)

})

//@AllArgsConstructor
@Getter
public abstract class Loan<T extends Loan> {
    private String type;

    private BigDecimal interestRate;

    private int term;

    private BigDecimal loanAmount;

    public Loan(String type, BigDecimal interestRate, int term, BigDecimal loanAmount) {
        if (term <= 0) {
            throw new IllegalArgumentException("Loan term must be positive.");
        }
        if (loanAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Loan amount must be positive.");
        }

        this.type = type;
        this.interestRate = interestRate;
        this.term = term;
        this.loanAmount = loanAmount;
    }
    public abstract CalculateResponse calculatePayment(T loan);
}
