package org.mhh.Filters;

import jakarta.servlet.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.logging.Logger;

public class AuthoritiesLoiteringFilter implements Filter {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            //this filter is call for every request (even user send first logging before authentication)
            // log user details and  her authorities after the log in is successfully
            LOG.info("User: " + authentication.getName() + " is successfully authenticated and has authorities"
                    + authentication.getAuthorities().toString());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
