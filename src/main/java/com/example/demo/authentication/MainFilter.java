package com.example.demo.authentication;

import com.example.demo.constant.CommonConstant;
import com.example.demo.model.User;
import com.example.demo.response.HTTPStatus;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class MainFilter implements Filter {

    @Autowired
    private AppScopeContext context;

    private List<String> urlNeedFilter = new ArrayList<>();

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        urlNeedFilter.add("/address-user/add-new/.*");
        urlNeedFilter.add("/user/save-book-buy-later");
        urlNeedFilter.add("/user/del-book-buy-later");

        String requestPath = request.getRequestURI();
        try {
            if (checkURLPatterns(urlNeedFilter, requestPath)) {
                String token = request.getParameter(CommonConstant.USER_TOKEN);
                User user = userService.getUserByToken(token);
                if (null != user && user.getIsActive()) {
                    context.setToken(token);
                    context.setCurrentUser(user);
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(HTTPStatus.UNAUTHORIZED.getCode(), HTTPStatus.UNAUTHORIZED.getMessage());
                }
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {
            response.sendError(HTTPStatus.SERVER_ERROR.getCode(), HTTPStatus.SERVER_ERROR.getMessage());
        }
        System.out.println(requestPath);

    }

    private static boolean checkURLPatterns(List<String> urlNeedFilter, String requestPath) {
        for (String url : urlNeedFilter) {
            if (Pattern.matches(url, requestPath)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<String> urlNeedFilter = new ArrayList<>();
        urlNeedFilter.add("/address-user/add-new/.");
        System.out.println(checkURLPatterns(urlNeedFilter, "/address-user/add-new/31"));
    }
}
