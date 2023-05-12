package com.visma.loan.calculation.entity.base;

import com.visma.loan.calculation.entity.HousingLoan;
import com.visma.loan.calculation.entity.SpendingLoan;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.visma.loan.calculation.response.CalculateResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;

import java.math.BigDecimal;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = HousingLoan.class, name = Type.HOUSING_LOAN),
        @JsonSubTypes.Type(value = SpendingLoan.class, name = Type.SPENDING_LOAN)

})

@Getter
public abstract class Loan<T extends Loan> {
    private String type;

    @Parameter(hidden = true)
    private BigDecimal interestRate;

    private int loanPeriodYear;

    private BigDecimal loanAmount;

    public Loan(String type, BigDecimal interestRate, int loanPeriodYear, BigDecimal loanAmount) {
        if (loanPeriodYear <= 0) {
            throw new IllegalArgumentException("Loan Period  must be positive.");
        }
        if (loanAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Loan amount must be positive.");
        }

        this.type = type;
        this.interestRate = interestRate;
        this.loanPeriodYear = loanPeriodYear;
        this.loanAmount = loanAmount;
    }
    public abstract CalculateResponse calculatePayment(T loan);
}
