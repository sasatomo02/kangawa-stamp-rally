package com.example.kangawa_stamp_rally.controller;

import com.example.kangawa_stamp_rally.config.AppProperties; // 作成した設定クラスをインポート

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ResponseBody
@RestController
@RequestMapping("/api/stamps/images")
public class StampImageController {

    private final Path fileStorageLocation;

    public StampImageController(AppProperties appProperties) {

        this.fileStorageLocation = Paths.get(appProperties.getImageStorageLocation()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
            System.out.println("画像保存ディレクトリ: " + this.fileStorageLocation.toString() + " の存在を確認しました。");
        } catch (IOException e) {
            throw new RuntimeException("画像保存ディレクトリを作成できませんでした: " + this.fileStorageLocation.toString(), e);
        }
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = "application/octet-stream";
                try {
                    String detectedContentType = Files.probeContentType(filePath);
                    if (detectedContentType != null) {
                        contentType = detectedContentType;
                    }
                } catch (IOException e) {
                    System.err.println("ファイルのMIMEタイプを判別できませんでした: " + filename + ": " + e.getMessage());
                }

                if (filename.toLowerCase().endsWith(".png")) {
                    contentType = MediaType.IMAGE_PNG_VALUE;
                } else if (filename.toLowerCase().endsWith(".jpg") || filename.toLowerCase().endsWith(".jpeg")) {
                    contentType = MediaType.IMAGE_JPEG_VALUE;
                } else if (filename.toLowerCase().endsWith(".gif")) {
                    contentType = MediaType.IMAGE_GIF_VALUE;
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                System.err.println("ファイルが見つからないか、読み取れません: " + filePath.toString());
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException ex) {
            System.err.println("ファイルパスの形式が不正です: " + filename + " - " + ex.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            System.err.println("ファイルの提供中に予期せぬエラーが発生しました " + filename + ": " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}