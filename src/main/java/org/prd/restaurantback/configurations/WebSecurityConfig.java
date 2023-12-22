package org.prd.restaurantback.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf( csrfConfig->csrfConfig.disable())
                .sessionManagement( managementConfig->managementConfig.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeHttpRequests(authConfig->{
                    authConfig.requestMatchers("/api/auth/**").permitAll();
                    //authConfig.requestMatchers("/api/auth/**").permitAll();
                    //authConfig.requestMatchers(HttpMethod.GET,"/api/auth/login").permitAll();
                    authConfig.anyRequest().authenticated();
                });
        /*http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
                        .authenticated())
                .httpBasic(withDefaults())//con esto se puede hacer la autenticacion basica
                .formLogin(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);*/
        return http.build();



        //sessionManagement indica que no se va a usar sesiones, sino que se va a usar un token
        //sessionCreationPolicy indica que no se va a crear una sesion
        //STATELESS indica que no se va a guardar el estado de la sesion


    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("piero")
                .password(passwordEncoder().encode("piero"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }*/

}