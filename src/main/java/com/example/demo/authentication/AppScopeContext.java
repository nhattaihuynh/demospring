package com.example.demo.authentication;

import com.example.demo.model.User;

public interface AppScopeContext {

    User getCurrentUser();

    void setCurrentUser(User user);

    String getToken();

    void setToken(String token);
}
