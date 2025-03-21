package com.accio.Online_FIR_System.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.HiddenHttpMethodFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/", "/css/**", "/js/**").permitAll()
                .requestMatchers("/login", "/signup").permitAll()
                .requestMatchers("/register-complaint", "/manage-complaint", "/update-complain", "/complain-response", "/get-complain-response", "/view-evidence").hasRole("NORMAL")
                .requestMatchers("/OnlineFirSystem/add-user").permitAll()
                .requestMatchers("/OnlineFirSystem/complain-summary").permitAll()
                .requestMatchers("/OnlineFirSystem").hasRole("NORMAL")
                .requestMatchers("/OnlineFirSystem/add-officer").hasRole("ADMIN")
                .requestMatchers("/OnlineFirSystem/add-police-station", "/admin-home", "/add-officer", "/add-police-station").hasRole("ADMIN")
                .requestMatchers("/OnlineFirSystem/get-all-complains", "/officer-home").hasRole("OFFICER")
                .requestMatchers("/OnlineFirSystem/update-Complain-Status", "/update-complaint-status").hasRole("OFFICER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(login -> login
                        .loginPage("/login")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureUrl("/login?error=true") // Redirects on login failure
                        .permitAll()
                ).logout(logout -> logout
                        .logoutUrl("/logout") // Logout URL
                        .logoutSuccessUrl("/") // Redirect to home page after logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        /*
        UserDetails user1 = User
                .builder()
                .username("Dharam")
                .password(passwordEncoder().encode("dharam@123"))
                .roles("NORMAL")
                .build();

        UserDetails user2 = User
                .builder()
                .username("Neha")
                .password(passwordEncoder().encode("neha@123"))
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(user1,user2);
        */
        return new CustomUserDetailsService();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
