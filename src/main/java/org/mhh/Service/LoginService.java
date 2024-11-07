package org.mhh.Service;

import lombok.RequiredArgsConstructor;
import org.mhh.Model.Customer;
import org.mhh.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final CustomerRepository customerRepository;

    public Customer registerUser(Customer customer){
        return customerRepository.save(customer);
    }

}
