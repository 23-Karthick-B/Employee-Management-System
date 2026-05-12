package com.employee.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

   @Bean
   public UserDetailsManager userDetailsManager(){
      UserDetails admin = User.builder()
                          .username("admin").password(passwordEncoder().encode("admin"))
                          .roles("ADMIN").build();

         return new InMemoryUserDetailsManager(admin);
   }

   @Bean
   public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
      http.csrf(csfr -> csfr.disable())
      .authorizeHttpRequests(auth -> auth.requestMatchers("/ems/login").permitAll().anyRequest().authenticated())
      .httpBasic(Customizer.withDefaults());
      return http.build();
   }

}
