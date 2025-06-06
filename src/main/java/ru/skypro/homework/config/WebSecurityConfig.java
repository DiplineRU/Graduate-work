package ru.skypro.homework.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.skypro.homework.service.UserService;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Конфигурация безопасности приложения.
 * <p>
 * Настраивает:
 * <ul>
 *   <li>Цепочку фильтров безопасности</li>
 *   <li>Правила доступа к эндпоинтам</li>
 *   <li>Механизмы аутентификации</li>
 *   <li>Кодировщик паролей</li>
 * </ul>
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/ads", "/ads/image/**", "/users/image/**").permitAll() // Публичные эндпоинты
                        .antMatchers("/auth/**", "/register").permitAll()
                        .antMatchers("/users/**", "/ads/me/**").authenticated() // Для аутентифицированных
                        .antMatchers("/admin/**").hasRole("ADMIN") // Только для администраторов
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource, PasswordEncoder passwordEncoder) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT email AS username, password, enabled FROM users WHERE email = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT email AS username, role FROM users WHERE email = ?");
        return manager;
    }

    /**
     * Конфигурирует цепочку фильтров безопасности.
     *
     * @param http объект конфигурации HTTP безопасности
     * @return сконфигурированная цепочка фильтров
     * @throws Exception при ошибках конфигурации
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/swagger-resources/**"),
                                new AntPathRequestMatcher("/swagger-ui.html"),
                                new AntPathRequestMatcher("/v3/api-docs"),
                                new AntPathRequestMatcher("/webjars/**"),
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/register"),
                                new AntPathRequestMatcher("/ads")
                        ).permitAll()
                        .requestMatchers(
                                new AntPathRequestMatcher("/ads/**"),
                                new AntPathRequestMatcher("/users/**")
                        ).authenticated()
                )
                .httpBasic(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    /**
     * Предоставляет кодировщик паролей BCrypt.
     *
     * @return экземпляр BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}