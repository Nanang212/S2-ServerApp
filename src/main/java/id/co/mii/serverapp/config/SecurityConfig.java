package id.co.mii.serverapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .inMemoryAuthentication()
      .withUser("admin")
      .password("admin")
      .roles("ADMIN")
      .and()
      .withUser("user")
      .password("user")
      .roles("USER");
  }

  @Bean
  protected PasswordEncoder passaEncoder() {
    return NoOpPasswordEncoder.getInstance();
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
