package com.visma.loan.calculation.controller;

import com.visma.loan.calculation.entity.base.Loan;
import com.visma.loan.calculation.response.CalculateResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/loan")
public class LoanController {
    @PostMapping("/calculateMonthlyPayment")
    public CalculateResponse calculate(@RequestBody Loan loan) {
            return loan.calculatePayment(loan);
    }
}
