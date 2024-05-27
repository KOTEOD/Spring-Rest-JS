package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kata.spring.boot_security.demo.service.RoleInUserDetails;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;

    public RoleInUserDetails getRoleInUserDetails() {
        return roleInUserDetails;
    }
    @Autowired
    public void setRoleInUserDetails(RoleInUserDetails roleInUserDetails) {
        this.roleInUserDetails = roleInUserDetails;
    }

    private RoleInUserDetails roleInUserDetails;

    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Разрешить доступ ко всем на /new
                .antMatchers("/new/**").permitAll()
                // Разрешить доступ к /index и /edit пользователям с ролью USER
                .antMatchers("/index/**", "/edit/**").hasAnyRole("USER")
                // Разрешить доступ к /** пользователям с ролью ADMIN и SUPERADMIN
                .antMatchers("/**").hasAnyRole("ADMIN", "SUPERADMIN")
                // Все остальные запросы требуют аутентификации
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(new SuccessUserHandler()) // Обработчик успешной аутентификации
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/index") // Перенаправление на /index после выхода
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // аутентификация inMemory
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(getRoleInUserDetails());
        return authProvider;
    }
}