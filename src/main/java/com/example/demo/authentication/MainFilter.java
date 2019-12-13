package com.example.demo.authentication;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFilter implements Filter {

    @Autowired
    private AppScopeContext context;

    private List<String> urlNeedFilter = new ArrayList<>();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        urlNeedFilter.add("");
        String requestPath = request.getRequestURI();
        if (false) {
            filterChain.doFilter(request, response);
        }
        System.out.println(requestPath);
        filterChain.doFilter(request, response);
    }
}
