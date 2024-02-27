package com.logitrack.logisticmanagement.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.logitrack.logisticmanagement.Jwt.JwtRequestFilter;
import com.logitrack.logisticmanagement.Service.UserService;
 
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{
    private final UserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService,
                          JwtRequestFilter jwtRequestFilter,
                          PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
    }
 

    @Bean
    //authentication
    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withUsername("Basant")
//                .password(encoder.encode("Pwd1"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("John")
//                .password(encoder.encode("Pwd2"))
//                .roles("USER","ADMIN","HR")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
        return new UserService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/register", "/api/login").permitAll())
                .authorizeHttpRequests(requests -> requests.requestMatchers("/api/business/**", "/api/customer/**", "/api/driver/**")
                        .authenticated())
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
 
    
 
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    // }
 
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.cors().and().csrf().disable()
    //             .authorizeRequests()
    //             .antMatchers(HttpMethod.POST, "/api/register").permitAll()
    //             .antMatchers(HttpMethod.POST, "/api/login").permitAll()
    //             .antMatchers(HttpMethod.POST,"/api/business/cargo").hasAuthority("BUSINESS")
    //             .antMatchers(HttpMethod.POST,"/api/business/assign-cargo").hasAnyAuthority("BUSINESS")
    //             // .antMatchers(HttpMethod.POST,"/api/").hasAnyAuthority("authority_Name","authority_Name")
    //             .antMatchers(HttpMethod.PUT,"/api/driver/cargo").hasAnyAuthority("DRIVER")
    //             .antMatchers(HttpMethod.GET,"/api/business/cargo").hasAnyAuthority("BUSINESS")
    //             .antMatchers(HttpMethod.GET,"/api/business/drivers").hasAnyAuthority("BUSINESS")
    //             .antMatchers(HttpMethod.GET,"/api/customer/cargo-status").hasAnyAuthority("CUSTOMER")
    //             .antMatchers(HttpMethod.GET,"/api/driver/cargo").hasAnyAuthority("DRIVER")
 
    //             .anyRequest().authenticated()
    //             .and()
    //             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 
    //     http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    // }
 
    
}