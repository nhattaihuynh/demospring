package com.example.demo.authentication;

import com.example.demo.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppScopeContextImpl implements AppScopeContext {
    private String token;
    private User user;

    @Override
    public User getCurrentUser() {
        return this.user;
    }

    @Override
    public void setCurrentUser(User user) {
        this.user = user;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }
}
