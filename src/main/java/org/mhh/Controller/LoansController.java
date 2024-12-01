package org.mhh.Controller;

import lombok.RequiredArgsConstructor;
import org.mhh.Service.LoanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanService loanService;


    @GetMapping("/myLoans")
    public String getLoanDetail() {
        return loanService.getLoanDetails();
    }
}
