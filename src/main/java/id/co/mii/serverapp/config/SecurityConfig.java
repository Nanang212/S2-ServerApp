package id.co.mii.serverapp.config;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import id.co.mii.serverapp.models.AppUserDetailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AppUserDetailService AppUserDetailService;
   private PasswordEncoder passwordEncoder;

    @Override
    protected void configure (AuthenticationManagerBuilder auth)throws
    Exception{
        auth
        .userDetailsService(AppUserDetailService)
        .passwordEncoder(passwordEncoder);
    }
    //     .inMemoryAuthentication()
    //     .withUser("admin")
    //     .password("admin")
    //     .roles("ADMIN")
    //     .and()
    //     .withUser("User")
    //     .password("user")
    //     .roles("USER");
    // }
//       @Bean
//     protected PasswordEncoder passwordEncoder(){
//         return NoOpPasswordEncoder.getInstance();
//  }
  
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
        .csrf()
        .disable()
        .cors()
        .disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/registration")
        .permitAll()
        .antMatchers("/region")
        .hasRole("ADMIN")
        .antMatchers("/employee")
        .hasAnyRole("ADMIN", "USER")
        .antMatchers("/country")
        .hasRole("USER")
        .anyRequest()
        .authenticated()
        // .permitAll()
        .and()
        // .formLogin();
        .httpBasic();
    }
  }