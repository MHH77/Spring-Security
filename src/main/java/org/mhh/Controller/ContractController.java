package org.mhh.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractController {
    @GetMapping("/contract")
    public String getContractInquiryDetails(){
        return "contract going to save in DB";
    }
}
