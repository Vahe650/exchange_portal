package com.exchange.exchange_portal.exceptions;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MDCFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        String remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (remoteAddr == null || "".equals(remoteAddr)) {
            remoteAddr = request.getRemoteAddr();
        }

        String contextPath = request.getContextPath();
        String pathInfo = request.getPathInfo();
        String remoteHost = request.getRemoteHost();


        try (
                MDC.MDCCloseable remoteAddrMdc = MDC.putCloseable("remoteAddr", remoteAddr);
                MDC.MDCCloseable remoteHostMdc = MDC.putCloseable("remoteHost", remoteHost);
                MDC.MDCCloseable contextPathMdc = MDC.putCloseable("contextPath", contextPath);
                MDC.MDCCloseable pathInfoMdc = MDC.putCloseable("pathInfo", pathInfo);
        ) {
            filterChain.doFilter(request, response);
        }
    }
}
