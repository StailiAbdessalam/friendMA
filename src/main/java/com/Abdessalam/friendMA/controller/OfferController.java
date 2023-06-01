package com.Abdessalam.friendMA.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.Abdessalam.friendMA.dto.model.OfferDto;
import com.Abdessalam.friendMA.entity.Offer;
import com.Abdessalam.friendMA.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/all")
    public Page<OfferDto> getAllOffers(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return offerService.getAllOffers(page, size);
    }

    @PostMapping
    public ResponseEntity<OfferDto> createOffer(@RequestParam("images") MultipartFile[] files, @RequestParam Map<String, String> offerDto) throws FirebaseAuthException {
        return offerService.createOffer(offerDto, files);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<OfferDto>> getByUserId(@PathVariable Long id) {
        return offerService.getByUserId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable Long id) {
        return offerService.getOfferById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable Long id) {
        return offerService.deleteOffer(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOffer(@PathVariable Long id, @RequestBody OfferDto offerDto) {
        return offerService.updateOffer(id, offerDto);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<Page<OfferDto>> getByCity(@PathVariable String city) {
        return offerService.getAllByCity(city);
    }



}
