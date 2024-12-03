package com.example.qrcodeservice.service;

import com.example.qrcodeservice.utils.ValidationUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.util.Map;

public class QRCodeService {
    public BufferedImage generateQRCode(String contents, String correction, int size) throws WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        Map<EncodeHintType, ?> correctionLevel = ValidationUtil.validateCorrection(correction);
        BitMatrix bitMatrix = writer.encode(contents, BarcodeFormat.QR_CODE, size, size, correctionLevel);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
