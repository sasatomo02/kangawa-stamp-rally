package com.example.kangawa_stamp_rally.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {

    private String imageStorageLocation;
    private String imageBaseUrl;
}