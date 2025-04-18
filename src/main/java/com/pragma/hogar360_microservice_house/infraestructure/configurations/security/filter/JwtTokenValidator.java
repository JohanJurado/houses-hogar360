package com.pragma.hogar360_microservice_house.infraestructure.configurations.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pragma.hogar360_microservice_house.infraestructure.utils.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

import static com.pragma.hogar360_microservice_house.infraestructure.utils.constants.ExceptionConstants.*;
import static com.pragma.hogar360_microservice_house.infraestructure.utils.constants.InfrastructureConstants.*;

@Component
@RequiredArgsConstructor
public class JwtTokenValidator extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        try {
            if (jwtToken != null && jwtToken.startsWith(TOKEN_PREFIX)){
                jwtToken = jwtToken.substring(TOKEN_PREFIX_SIZE);

                DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

                setSecurityContext(extractEmail(decodedJWT), extractAuthorities(decodedJWT));
            }
        } catch (JWTVerificationException exception) {
            sendTokenValidationExceptions(response, HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
            return;
        } catch (Exception exception){
            sendTokenValidationExceptions(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, TOKEN_VALIDATION_FAILED_MESSAGE);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void sendTokenValidationExceptions(HttpServletResponse response, int status, String message)
            throws IOException {
        response.setContentType(CONTENT_TYPE);
        response.setStatus(status);
        response.getWriter().write(
                String.format(FORMAT_MESSAGE_EXCEPTION, message, status)
        );
    }

    private String extractEmail(DecodedJWT decodedJWT){
        return jwtUtils.extractUsername(decodedJWT);
    }

    private Collection<? extends GrantedAuthority> extractAuthorities(DecodedJWT decodedJWT){
        String stringAuthorities = jwtUtils.getSpecificClaim(decodedJWT, AUTHORITIES_CLAIM_KEY).asString();
        return AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);
    }

    private void setSecurityContext(String email, Collection<? extends GrantedAuthority> authorities){
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(createAuthenticationToken(email, authorities));
        SecurityContextHolder.setContext(context);
    }

    private Authentication createAuthenticationToken(String email, Collection<? extends GrantedAuthority> authorities){
        return new UsernamePasswordAuthenticationToken(email, null, authorities);
    }
}
