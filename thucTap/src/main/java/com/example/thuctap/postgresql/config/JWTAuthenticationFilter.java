package com.example.thuctap.postgresql.config;

import com.example.thuctap.postgresql.entity.Account;
import com.example.thuctap.postgresql.service.AccountService;
import com.example.thuctap.postgresql.service.impl.AccountServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {// OncePerRequestFilter  là một lớp trừu tượng trong Spring Framework, được thiết kế để đảm bảo rằng một bộ lọc chỉ được thực thi một lần duy nhất cho mỗi yêu cầu

    @Autowired
    JWTTokenProvider jwtTokenProvider;

    @Autowired
    AccountService accountService;

    // trích xuất token
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                Long idAccount = Long.parseLong(jwtTokenProvider.getAccountIdFromJWT(jwt));
                UserDetails userDetails = accountService.loadCustomsAccountByIdAccount(idAccount);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (Exception ex) {
            log.error("Failed on set account authentication", ex);
        }
        filterChain.doFilter(request, response);
    }
}
