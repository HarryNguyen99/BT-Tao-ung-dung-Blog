package com.spring.service;

import com.spring.model.AppUser;

public interface AppUserService {
    AppUser getUserByUsername(String username);
}
