package com.capstone.fitnessrx;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.util.AntPathMatcher;
import com.capstone.fitnessrx.Models.User;
import com.capstone.fitnessrx.Services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                /* Login configuration */
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home") // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page
                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login") // append a query string value
                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/","/workouts-wall", "/exercise-page", "/home", "/register", "/css/**", "/js/**","/workout-generator", "/img/**", "/error") // anyone can see home, the ads pages, and sign up
                .permitAll()
                /* Pages that require authentication */
                .and()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/profile/upload", "/feed/comment/delete/{id}", "/feed/post/edit/{id}", "/feed/post/delete/{id}", "/messages/{recipient}", "/sendMessage", "/message/{friendId}", "/messages/send", "/message", "/users", "/profile/{id}","feed/comment/create", "/feed/{id}", "/calender/{id}", "/my-workouts/{id}", "/map/{location}", "/favorites/{id}", "/workout-builder", "/exercise-display/{id}", "/workout-plan/{id}", "/calender/note", "/profile/delete", "/workout-builder-inator","/initialize-workout", "/workout/favorites"
                        // only authenticated users can edit ads
                )
                .authenticated()
        ;
        return http.build();
    }

}
