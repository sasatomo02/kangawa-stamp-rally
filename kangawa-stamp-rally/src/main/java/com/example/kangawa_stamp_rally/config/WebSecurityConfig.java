package com.example.kangawa_stamp_rally.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain; // DefaultSecurityFilterChain ではなく SecurityFilterChain を使用
import org.springframework.http.HttpMethod; // HttpMethod をインポート

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // メソッド名も securityFilterChain に変更
        http
                .csrf(csrf -> csrf.disable()) // 開発・APIテスト用。本番では適切に設定
                .authorizeHttpRequests(auth -> auth
                        // 1. CORSプリフライトリクエスト (OPTIONSメソッド) をすべてのパスで許可
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // 2. その他の許可したいパス
                        .requestMatchers(
                                "/",
                                "/images/**",
                                "/css/**",
                                "/js/**",
                                "/webjars/**",
                                "/authorization/**",
                                "/filter-error",
                                "/top",
                                "/api/stamps/images/**", // /api/stamps/images/以下の全パス
                                "/add",
                                "/quiz",
                                "/swagger-ui.html",
                                "/swagger-ui/**", // Swagger UIの静的リソースも許可
                                "/v3/api-docs/**", // OpenAPI SpecificationのJSON/YAMLも許可
                                "/test", // もし/testというAPIエンドポイントがあるなら
                                "/user" // もし/userというAPIエンドポイントがあるなら
                        ).permitAll()

                        // 3. 上記以外のすべてのリクエストは認証が必要
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}