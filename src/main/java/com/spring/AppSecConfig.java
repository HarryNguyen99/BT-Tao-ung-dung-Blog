package com.spring;

import com.spring.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AppUserService appUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser("user").password(encoder.encode("123456")).roles("USER")
//                .and()
//                .withUser("admin").password(encoder.encode("123456")).roles("ADMIN");

        auth.userDetailsService((UserDetailsService) appUserService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/blog/delete/**").access("hasRole('ADMIN')")
                .antMatchers("/blog/create**").access("hasRole('USER')")
                .antMatchers("/blog/edit/**").access("hasRole('USER')")
                .antMatchers("/category/delete/**").access("hasRole('ADMIN')")
                .antMatchers("/category/create/**").access("hasRole('USER')")
                .antMatchers("/category/edit/**").access("hasRole('USER')")
                .and()
                .formLogin()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and().exceptionHandling()
                // .accessDeniedHandler(new MyAccessDeniedHandler())
                .accessDeniedPage("/error")

        ;
        http.csrf().disable();
    }

}
