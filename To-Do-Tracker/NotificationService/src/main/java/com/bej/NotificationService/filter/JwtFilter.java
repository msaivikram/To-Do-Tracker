package com.bej.NotificationService.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        ServletOutputStream pw = response.getOutputStream();
        // Parse and validate the token and set the user id from claims in the request header as an attribute.
        //expects the token to come from the header
        String authHeader=request.getHeader("Authorization");
        System.out.println(authHeader);
        if(authHeader==null || !authHeader.startsWith("Bearer")){
            throw new ServletException("Missing or invalid Token");
        }


        String token= authHeader.substring(7);
        Claims claim= Jwts.parser().setSigningKey("confidential").parseClaimsJws(token).getBody();
        System.out.println(claim);
        if (claim == null) {
            throw new ServletException("Invalid Token");
        }
        request.setAttribute("Claims",claim);
        filterChain.doFilter(request, response);
    }
}
