package com.example.demo.qr;

import com.example.demo.good.GoodRepository;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QRCodeService {

    GoodRepository goodRepository;

    @Autowired
    public void QRService(GoodRepository goodRepository){
        this.goodRepository = goodRepository;
    }

    public byte[] generate(Long goodId, int width, int height) {
        try (ByteArrayOutputStream bos = QRCode.from(goodRepository.getById(goodId).toString()).withSize(width, height).stream(); ) {
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
