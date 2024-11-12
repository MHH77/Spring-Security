package org.mhh.Controller;

import lombok.RequiredArgsConstructor;
import org.mhh.Model.Customer;
import org.mhh.Service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerUser(@RequestBody Customer customer) {
        //before save in database convert password (plain text) to hash and then save in database
        String hashPwd = passwordEncoder.encode(customer.getPwd());
        customer.setPwd(hashPwd);
        Customer registeredUser = loginService.registerUser(customer);
        if (registeredUser.getId() > 0) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(registeredUser);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

}
