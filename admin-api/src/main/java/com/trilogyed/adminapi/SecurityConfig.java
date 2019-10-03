package com.trilogyed.adminapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  DataSource dataSource;

  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {

    PasswordEncoder encoder = new BCryptPasswordEncoder();

    authBuilder.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(
                    "select username, password, enabled from users where username = ?")
            .authoritiesByUsernameQuery(
                    "select username, authority from authorities where username = ?")
            .passwordEncoder(encoder);
  }

  public void configure(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.httpBasic();

    httpSecurity.authorizeRequests()
            .mvcMatchers(HttpMethod.POST, "/customers").hasAuthority("ROLE_MANAGER")
            .mvcMatchers(HttpMethod.GET, "/customers/{id}").hasAuthority("ROLE_EMPLOYEE")
            .mvcMatchers(HttpMethod.GET, "/customers/all").hasAuthority("ROLE_EMPLOYEE")
            .mvcMatchers(HttpMethod.PUT, "/customers/update/{id}").hasAuthority("ROLE_TEAM_LEAD")
            .mvcMatchers(HttpMethod.DELETE, "/customers/delete/{id}").hasAuthority("ROLE_ADMIN")
            .mvcMatchers(HttpMethod.POST, "/inventory").hasAuthority("ROE_MANAGER")
            .mvcMatchers(HttpMethod.GET, "/inventory/{id}").hasAuthority("ROLE_EMPLOYEE")
            .mvcMatchers(HttpMethod.GET, "/inventory/product/{id}").hasAuthority("ROLE_EMPLOYEE")
            .mvcMatchers(HttpMethod.GET, "/inventory/all").hasAuthority("ROLE_EMPLOYEE")
            .mvcMatchers(HttpMethod.PUT, "/inventory/update/{id}").hasAuthority("ROLE_EMPLOYEE")
            .mvcMatchers(HttpMethod.DELETE, "/inventory/delete/{id}").hasAuthority("ROLE_ADMIN")
            .mvcMatchers(HttpMethod.POST, "/invoices").hasAuthority("ROLE_MANAGER")
            .mvcMatchers(HttpMethod.GET, "/invoices/{id}").hasAuthority("ROLE_EMPLOYEE")
            .mvcMatchers(HttpMethod.GET, "/invoices/customer/{id}").hasAuthority("ROLE_EMPLOYEE")
            .mvcMatchers(HttpMethod.GET, "/invoices/all").hasAuthority("ROLE_EMPLOYEE")
            .mvcMatchers(HttpMethod.PUT, "/invoices/update/{id}").hasAuthority("ROLE_TEAM_LEAD")
            .mvcMatchers(HttpMethod.DELETE, "/invoices/delete/{id}").hasAuthority("ROLE_ADMIN")
            .mvcMatchers(HttpMethod.POST, "/levelups").hasAuthority("ROLE_MANAGER")
            .mvcMatchers(HttpMethod.GET, "/levelups/{id}").hasAuthority("ROLE_EMPLOYEE")
            .mvcMatchers(HttpMethod.GET, "/levelups/all").hasAuthority("ROLE_EMPLOYEE")
            .mvcMatchers(HttpMethod.PUT, "/levelups/update/{id}").hasAuthority("ROLE_TEAM_LEAD")
            .mvcMatchers(HttpMethod.DELETE, "/levelups/delete/{id}").hasAuthority("ROLE_ADMIN")
            .mvcMatchers(HttpMethod.POST, "/products").hasAuthority("ROLE_MANAGER")
            .mvcMatchers(HttpMethod.GET, "/products/{id}").hasAuthority("ROLE_EMPLOYEE")
            .mvcMatchers(HttpMethod.GET, "/products/all").hasAuthority("ROLE_EMPLOYEE")
            .mvcMatchers(HttpMethod.PUT, "/products/update/{id}").hasAuthority("ROLE_TEAM_LEAD")
            .mvcMatchers(HttpMethod.DELETE, "/products/delete/{id}").hasAuthority("ROLE_ADMIN")
            .anyRequest().permitAll();

    httpSecurity
            .logout()
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/logout")
            .deleteCookies("JSESSIONID")
            .deleteCookies("XSRF_TOKEN")
            .invalidateHttpSession(true);

    httpSecurity
            .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

  }
}
