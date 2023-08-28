package com.example.auth_final2.config;


import com.example.auth_final2.service.UserInforUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // to indicate that a class is a configuration class.
@EnableWebSecurity // it indicates that the application is using Spring Security for securing the web requests.
@EnableMethodSecurity // allows us to use roles  in our system.
public class SecurityConfig {
    // used to have 2 overloaded config methods , now we have to do it manually
    // we have to create a bean for each
    //  1.AuthenticationManager ie creation of users ie InMemoryUsers Replaced
    //  1.1 Replaced with UserDetailService to create authentication Related Stuff
    // 2.HttpSecurity endpoints
    // 2.1 Replaced with SecurityFilter chain
    //
    @Bean
    public UserDetailsService userDetailsService(){
//        UserDetails superAdmin  = User.withUsername("sAdmin")
//                .password(encoder.encode("sAdmin"))
//                .roles("ADMIN" , "USER")
//                .build();
//        UserDetails admin  = User.withUsername("admin")
//                .password(encoder.encode( "admin"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user  = User.withUsername("user")
//                .password(encoder.encode("user"))
//                .roles("USER")
//                .build();
//        return  new InMemoryUserDetailsManager(superAdmin , admin, user);
        return  new UserInforUserDetailsService();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer ->customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/login" , "/register", "/messages").permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return  http.build();

    }

    // It is not good to keep password plan , we create another bean which encrypt it
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider( );
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }



}
