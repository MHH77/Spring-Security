package org.mhh.Controller;

import lombok.RequiredArgsConstructor;
import org.mhh.Model.Customer;
import org.mhh.Service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        Customer registeredUser = loginService.registerUser(customer);
        ResponseEntity responseEntity = null;
        if (registeredUser.getId() > 0) {
            return responseEntity = ResponseEntity.status(HttpStatus.CREATED)
                    .body(registeredUser);
        } else {
            return responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("register not Succesfully!");
        }

    }

}
