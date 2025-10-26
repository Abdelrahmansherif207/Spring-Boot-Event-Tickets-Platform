package com.devdodge.tickets.filters;

import com.devdodge.tickets.Entites.User;
import com.devdodge.tickets.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserProvisioningFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        // TODO
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(
                authentication != null &&
                authentication.isAuthenticated() &&
                authentication.getPrincipal() instanceof Jwt jwt) {
            UUID keyclockId= UUID.fromString(jwt.getSubject());

            if(!userRepository.existsById(keyclockId)) {
                User newUser = new User();
                newUser.setId(keyclockId);
                newUser.setName(jwt.getClaimAsString("preferred_username"));
                newUser.setEmail(jwt.getClaimAsString("email"));
                userRepository.save(newUser);
            }
        }

        filterChain.doFilter(request, response);
    }
}
