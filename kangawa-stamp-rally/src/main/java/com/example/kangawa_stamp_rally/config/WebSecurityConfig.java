package com.example.kangawa_stamp_rally.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain; // DefaultSecurityFilterChain ではなく SecurityFilterChain を使用
import org.springframework.http.HttpMethod; // HttpMethod をインポート
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // メソッド名も securityFilterChain に変更
        http
                .csrf(csrf -> csrf.disable()) // 開発・APIテスト
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/HTML_img/**", "/css/**", "/js/**", "/img/**").permitAll()
                        // CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // その他の許可したいパス
                        .requestMatchers(
                                "/",
                                "/images/**",
                                "/css/**",
                                "/js/**",
                                "/webjars/**",
                                "/authorization/**",
                                "/filter-error",
                                "/top",
                                "/api/stamps/images/**",
                                "/add",
                                "/quiz",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/test",
                                "/user",
                                "complete",
                                "events"
                        ).permitAll()

                        // 3. 上記以外のすべてのリクエストは認証が必要
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Vercelからのアクセスを許可
        configuration.setAllowedOrigins(Arrays.asList(
                //本番環境
                "https://kangawa-stamp-rally.vercel.app/",
                "http://localhost:5173",
                "https://kangawa-stamp-rally-phi.vercel.app/"
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}