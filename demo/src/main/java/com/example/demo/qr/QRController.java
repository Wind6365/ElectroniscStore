package com.example.demo.qr;

import com.example.demo.good.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class QRController {
    private final int WIDTH = 250;
    private final int HEIGHT = 250;
    private final String QR_TEXT = "Spring Boot REST API to generate QR Code - Websparrow.org";

    private final QRCodeService qrCodeService;

    @Autowired
    public QRController(QRCodeService qrCodeService){
        this.qrCodeService = qrCodeService;
    }

    @GetMapping("qr-code/{goodId}")
    public ResponseEntity<byte[]> getQrCode(@PathVariable("goodId") Long goodId) {

        byte[] qrImage = qrCodeService.generate(goodId, WIDTH, HEIGHT);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrImage);
    }
}
