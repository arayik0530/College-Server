package com.lnTime.config;

import com.lnTime.security.jwt.JwtConfigurer;
import com.lnTime.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private  JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().and()
                .authorizeRequests()
                .antMatchers("/swagger-resources/*").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/items/**/all-images").permitAll()
                .antMatchers("/api/items/**/show").permitAll()
                .antMatchers("/api/items/**/path").permitAll()
                .antMatchers("/api/items/images/**").permitAll()
                .antMatchers("/api/items/last/**/**").permitAll()
                .antMatchers("/api/categories/**/show").permitAll()
                .antMatchers("/api/subCategories/**/show").permitAll()
                .antMatchers("/api/categories/**/show/subCategories").permitAll()
                .antMatchers("/api/categories/**/path").permitAll()
                .antMatchers("/api/subCategories/**/path").permitAll()
                .antMatchers("/api/subCategories/**/show/items").permitAll()
                .antMatchers("/api/slider-images/delete/**").authenticated()
                .antMatchers("/api/slider-images/add").authenticated()
                .antMatchers("/api/slider-images/**").permitAll()
                .antMatchers("/api/slider-images/allIds").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

}