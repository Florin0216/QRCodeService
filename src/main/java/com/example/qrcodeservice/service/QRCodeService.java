package com.example.qrcodeservice.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.util.Map;

public class QRCodeService {
    public BufferedImage generateQRCode(String contents,String correction, int size) throws WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        Map<EncodeHintType, ?> hints = switch(correction){
            case "L" -> Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            case "M" -> Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            case "Q" -> Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
            case "H" -> Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            default -> null;
        };
        BitMatrix bitMatrix = writer.encode(contents, BarcodeFormat.QR_CODE, size, size, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
