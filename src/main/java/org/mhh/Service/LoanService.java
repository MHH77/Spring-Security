package org.mhh.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mhh.Repository.LoanRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    @PostAuthorize("hasRole('ADMIN')")
    public String getLoanDetails() {
        log.info("Get loan details");
        return "Here are the loans detail from the DB";
    }


}
