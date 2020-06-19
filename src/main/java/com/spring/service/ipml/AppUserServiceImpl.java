package com.spring.service.ipml;

import com.spring.model.AppUser;
import com.spring.repository.AppUserRepository;
import com.spring.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public AppUser getUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = this.getUserByUsername(username);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(user.getRole());

        UserDetails userDetails = new User(
                user.getUsername(),
                user.getPass(),
                authorities
        );

        return userDetails;
    }
}
