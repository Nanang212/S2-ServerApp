package id.co.mii.serverapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import id.co.mii.serverapp.services.AppUserDetailService;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private AppUserDetailService appUserDetailService;
    private PasswordEncoder passwordEncoder;

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(appUserDetailService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .antMatchers(HttpMethod.GET, "/auth/**")
                        .permitAll()
                        .antMatchers(HttpMethod.POST, "/auth/**")
                        .permitAll()
                        .antMatchers(HttpMethod.POST, "/verify-account/**")
                        .permitAll()
                        .antMatchers(HttpMethod.GET, "/verify-account/**")
                        .permitAll()
                        .antMatchers(HttpMethod.GET, "/images/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .csrf()
                .disable()
                .cors()
                .disable()
                // .formLogin();
                .httpBasic();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
