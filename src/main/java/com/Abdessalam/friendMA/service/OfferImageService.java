package com.Abdessalam.friendMA.service;

import com.Abdessalam.friendMA.entity.Offer;
import com.Abdessalam.friendMA.entity.OfferImage;
import com.Abdessalam.friendMA.repository.OfferImageRepository;
import com.Abdessalam.friendMA.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OfferImageService {

    private final OfferImageRepository offerImageRepository;

    public List<String> getAllOfferImages(Long offerId) {
        return offerImageRepository.findAllByOfferId(offerId).stream()
                .map(OfferImage::getImageUrl)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public void saveOfferImages(Offer offer, MultipartFile[] files) {

        FileUtil.createDirIfNotExist();
        List<OfferImage> offerImages = new ArrayList<>();
        Arrays.stream(files).forEach(file -> {
            byte[] bytes;
            try {
                bytes = file.getBytes();
                String extension = FileUtil.getExtension(Objects.requireNonNull(file.getOriginalFilename()));
                String fileName = UUID.randomUUID() + extension;
                Files.write(Paths.get(FileUtil.folderPath + fileName), bytes);

                OfferImage offerImage = new OfferImage();
                offerImage.setImage(fileName);
                offerImage.setOffer(offer);
                offerImages.add(offerImage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        offerImageRepository.saveAll(offerImages);
    }

    public ResponseEntity<byte[]> getImage(String image) {
        try {
            return ResponseEntity.ok(Files.readAllBytes(Paths.get("uploads/" + image)));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
