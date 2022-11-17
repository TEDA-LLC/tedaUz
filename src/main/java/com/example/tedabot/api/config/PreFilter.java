package com.example.tedabot.api.config;

import com.example.tedabot.api.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;


@Component
@RequiredArgsConstructor
public class PreFilter extends OncePerRequestFilter {
    @Value("${telegram.bot.token}")
    String botToken;


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String token = request.getHeader("Authorization");
        try {
            if (token != null && token.length() > 8) {
                token = token.substring(7);
                if (!token.equals(botToken)) {
                    throw new AccessDeniedException("Forbidden !");
                }
            } else throw new AccessDeniedException("Forbidden !");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Headers", "*");
            response.addHeader("Access-Control-Allow-Methods", "*");
            response.addHeader("Access-Control-Allow-Credentials", "false");
            filterChain.doFilter(request, response);
        } catch (AccessDeniedException exception) {
            response.setStatus(403);
            response.getWriter().print(ApiResponse.builder().
                    message("Forbidden").
                    status(403).
                    success(false).
                    build());

        }
    }
}
