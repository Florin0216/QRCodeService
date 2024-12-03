package com.example.qrcodeservice.controller;

import com.example.qrcodeservice.service.QRCodeService;
import com.example.qrcodeservice.utils.ValidationUtil;
import com.google.zxing.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.awt.image.BufferedImage;


@RestController
@RequestMapping("/api")
public class ApiController {

    private final QRCodeService qrCodeService;

    public ApiController() {
        this.qrCodeService = new QRCodeService();
    }

    @GetMapping("/health")
    public ResponseEntity getHealth() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/qrcode")
    public ResponseEntity<?> getImage(@RequestParam(name = "contents") String contents,
                                      @RequestParam(name = "correction", defaultValue = "L", required = false) String correction,
                                      @RequestParam(name = "size", defaultValue = "250", required = false) int size,
                                      @RequestParam(name = "type", defaultValue = "png", required = false) String type) throws WriterException {
        Map<String, String> validationError = ValidationUtil.validateInputs(contents, correction, size, type);
        if (validationError != null) {
            return ResponseEntity.badRequest().body(validationError);
        }

        BufferedImage bufferedImage = qrCodeService.generateQRCode(contents, correction, size);

        MediaType mediaType = ValidationUtil.validatePhotoType(type);

        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .body(bufferedImage);
    }

}