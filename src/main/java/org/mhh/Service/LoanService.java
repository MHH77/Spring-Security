package org.mhh.Service;

import lombok.RequiredArgsConstructor;
import org.mhh.Repository.LoanRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public String getLoanDetails() {
        return "Here are the loans detail from the DB";
    }


}
