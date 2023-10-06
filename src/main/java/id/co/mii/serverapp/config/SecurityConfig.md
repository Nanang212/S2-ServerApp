# Security Config

## Authentication Provider JPA Deprecated Version
```java

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private AppUserDetailService appUserDetailService;
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
            .userDetailsService(appUserDetailService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) {
        http
            .csrf()
            .disable()
            .cors()
            .disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/registration")
            .permitAll()
            .anyRequest()
            .authenticated()
            // .permitAll()
            .and()
            // .formLogin();
            .httpBasic();
    }
}
```

## Authentication Provider In Memory
```java
@EnableMethodSecurity
@AllArgsConstructor
class SecurityConfig2 {

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
            .username("user")
            .password("user")
            .roles("USER")
            .build();
        UserDetails admin = User.builder()
            .username("admin")
            .password("admin")
            .roles("USER", "ADMIN")
            .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .antMatchers(HttpMethod.POST, "/registration").permitAll()
                .antMatchers("/region/**", "/regions").permitAll()
                .antMatchers("/country/**", "/countries").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic();

        return http.build();
    }
}
```