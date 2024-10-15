package admin_user.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import admin_user.service.CustomSuccessHandler;
import admin_user.service.CustomUserDetailService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    CustomSuccessHandler customSuccessHandler;
    
    @Autowired
    CustomUserDetailService customUserDetailService;
    
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http.csrf(c -> c.disable())
        
        // Authorization: mapping roles to different paths
        .authorizeHttpRequests(request -> request
                .requestMatchers("/admin-page").hasAuthority("ADMIN")
                .requestMatchers("/faculty/dashboard").hasAuthority("FACULTY")
                .requestMatchers("/student/dashboard").hasAuthority("STUDENT") // Student-specific endpoints
                .requestMatchers("/registration", "/css/**").permitAll()
                .anyRequest().authenticated())
        
        // Login configuration
        .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(customSuccessHandler) // Custom handler to redirect based on role
                .permitAll())
        
        // Logout configuration
        .logout(form -> form
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll());
        
        return http.build();
    }
    
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }
}
