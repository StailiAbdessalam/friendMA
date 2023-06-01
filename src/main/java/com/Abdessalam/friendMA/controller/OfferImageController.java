package com.Abdessalam.friendMA.controller;

import com.Abdessalam.friendMA.service.OfferImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class OfferImageController {

    private final OfferImageService offerImageService;

    @GetMapping(value = "{image}",produces = "image/png")
    public ResponseEntity<byte[]> getImage(@PathVariable String image) {
        return offerImageService.getImage(image);
    }

    @GetMapping("offer/{offerId}")
    public List<String> getAllOfferImages(@PathVariable Long offerId) {
        return offerImageService.getAllOfferImages(offerId);
    }
}
