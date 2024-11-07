package org.mhh.Config;

import lombok.AllArgsConstructor;
import org.mhh.Model.Customer;
import org.mhh.Repository.CustomerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service//this is my custom service authentication
@AllArgsConstructor
public class CustomUserDetails implements UserDetailsService {

    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities =new ArrayList<>();
        List<Customer> customers = customerRepository.findByEmail(username);
        String pwd;
        if (customers.size() == 0) {
            throw new UsernameNotFoundException("User details not found for the user :".concat(username));
        } else {
            String email = customers.get(0).getEmail();
            pwd = customers.get(0).getPwd();
            String role = customers.get(0).getRole();
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return new User(username, pwd, authorities);
    }
}
