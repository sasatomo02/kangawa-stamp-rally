package com.example.kangawa_stamp_rally.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {
    // app.image-storage-location プロパティに対応
    private String imageStorageLocation;

    // app.image-base-url プロパティに対応
    private String imageBaseUrl;
}