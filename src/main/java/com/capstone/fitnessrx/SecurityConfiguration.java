//package com.capstone.fitnessrx;
//
//import com.capstone.fitnessrx.Services.UserDetailsLoader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    private UserDetailsLoader usersLoader;
//
//    public SecurityConfiguration(UserDetailsLoader usersLoader) {
//        this.usersLoader = usersLoader;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        //noinspection removal
//        http
//                /* Login configuration */
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/home") // user's home page, it can be any URL
//                .permitAll() // Anyone can go to the login page
//                /* Logout configuration */
//                .and()
//                .logout()
//                .logoutSuccessUrl("/home") // append a query string value
//                /* Pages that can be viewed without having to log in */
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/", "/home", "/register") // anyone can see home, the ads pages, and sign up
//                .permitAll()
//                /* Pages that require authentication */
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers(
//                        "/ads/create", // only authenticated users can create ads
//                        "/ads/{id}/edit" // only authenticated users can edit ads
//                )
//                .authenticated()
//        ;
//        return http.build();
//    }
//
//}