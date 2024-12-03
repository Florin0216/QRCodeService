package com.example.qrcodeservice.utils;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.http.MediaType;

import java.util.Map;

public class ValidationUtil {
    public static Map<String, String> validateInputs(String contents, String correction, int size, String type) {
        if (contents == null || contents.trim().isEmpty()) {
            return Map.of("error", "Contents cannot be null or blank");
        }
        if (size < 150 || size > 350) {
            return Map.of("error", "Image size must be between 150 and 350 pixels");
        }
        if (!correction.equals("L") && !correction.equals("M") && !correction.equals("Q") && !correction.equals("H")) {
            return Map.of("error", "Permitted error correction levels are L, M, Q, H");
        }
        if (!type.equals("png") && !type.equals("jpeg") && !type.equals("gif")) {
            return Map.of("error", "Only png, jpeg and gif image types are supported");
        }
        return null;
    }

    public static MediaType validatePhotoType(String type) {
        return switch (type) {
            case "png" -> MediaType.IMAGE_PNG;
            case "jpeg" -> MediaType.IMAGE_JPEG;
            case "gif" -> MediaType.IMAGE_GIF;
            default -> null;
        };
    }

    public static Map<EncodeHintType, ?> validateCorrection(String correction) {
        return switch (correction) {
            case "L" -> Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            case "M" -> Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            case "Q" -> Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
            case "H" -> Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            default -> null;
        };
    }
}
