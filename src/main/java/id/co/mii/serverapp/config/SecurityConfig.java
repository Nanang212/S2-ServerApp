package id.co.mii.serverapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import id.co.mii.serverapp.services.AppUserDetailService;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AppUserDetailService appUserDetailService;
   private PasswordEncoder passwordEncoder;

    @Override
    protected void configure (AuthenticationManagerBuilder auth)throws
    Exception{
        auth
        .userDetailsService(appUserDetailService)
        .passwordEncoder(passwordEncoder);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean()throws Exception{
        return super .authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
     http
        .csrf()
        .disable()
        .cors()
        .disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/registration")
        .permitAll()
        // .antMatchers(HttpMethod.POST, "/registrasi").permitAll()
        .antMatchers(HttpMethod.POST, "/login")
        .permitAll()
        .anyRequest()
        // .permitAll()
        .authenticated()
        .and()
        .httpBasic();
    }
}