package org.mhh.Config;

import lombok.AllArgsConstructor;
import org.mhh.Model.Authority;
import org.mhh.Model.Customer;
import org.mhh.Repository.CustomerRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        List<Customer> customers = customerRepository.findByEmail(name);//load form database
        if (!customers.isEmpty()) {
            if (passwordEncoder.matches(pwd, customers.get(0).getPwd())) {
                return new UsernamePasswordAuthenticationToken(name, pwd, getGrantedAuthorities(customers.get(0).getAuthorities()));
            } else {
                throw new BadCredentialsException("Invalid password");
            }

        } else {
            throw new BadCredentialsException("User not found");
        }
    }

    //helper method for fetch authorities that store in database for per user
    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //authentication type based on username and password
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
