package com.Abdessalam.friendMA.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.Abdessalam.friendMA.dto.mapper.MapString;
import com.Abdessalam.friendMA.dto.mapperInterface.IMapperDto;
import com.Abdessalam.friendMA.dto.model.OfferDto;
import com.Abdessalam.friendMA.entity.Offer;
import com.Abdessalam.friendMA.entity.User;
import com.Abdessalam.friendMA.enumeration.RoomType;
import com.Abdessalam.friendMA.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OfferService {


    private final OfferRepository offerRepository;
    private final UserService userService;
    private final IMapperDto<Offer, OfferDto> offerMapper;
    private final MapString<Map<String,String>,Offer> mapString;
    private final OfferImageService offerImageService;


    public Page<OfferDto> getAllOffers(int page,int size) {
        log.info("getAllOffers");
        Pageable pageable = PageRequest.of(page, size);
        return offerMapper.convertPageToPageDto(offerRepository.findAll(pageable), OfferDto.class);
    }

    public ResponseEntity<OfferDto> createOffer(Map<String,String> offerDto, MultipartFile[] files) throws FirebaseAuthException {
        Offer offer = mapString.convertToEntity(offerDto, Offer.class);

        offer.setAvailableFrom(LocalDate.parse(offerDto.get("availableFrom")));
        System.out.println("createOffer" + offer.getAvailableFrom());

        UserRecord userRecord = FirebaseAuth.getInstance().getUser(offer.getUserId());

        offer.setCity(offer.getCity().toLowerCase());
        Offer offerSaved = offerRepository.save(offer);
        offerImageService.saveOfferImages(offerSaved, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(offerMapper.convertToDto(offerSaved, OfferDto.class));
    }

    public ResponseEntity<List<OfferDto>> getByUserId(Long id) {
        List<Offer> offers = offerRepository.findAllByUserId(id);
        if(offers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(offerMapper.convertListToListDto(offers,OfferDto.class));
    }

    public ResponseEntity<OfferDto> getOfferById(Long id) {
        Offer offer = offerRepository.findById(id).orElse(null);
        if(offer == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(offerMapper.convertToDto(offer,OfferDto.class));
        }
    }

    public ResponseEntity<String> deleteOffer(Long id) {
        Offer offer = offerRepository.findById(id).orElse(null);
        if(offer == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Offer not found");
        } else {
            offerRepository.delete(offer);
            return ResponseEntity.status(HttpStatus.OK).body("Offer deleted successfully");
        }
    }

    public ResponseEntity<String> updateOffer(Long id, OfferDto offerDto) {
        Offer offer = offerRepository.findById(id).orElse(null);
        if(offer == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Offer not found");
        } else {
            offer.setTitle(offerDto.getTitle());
            offer.setDescription(offerDto.getDescription());
            offer.setCity(offerDto.getCity());
            offer.setAddress(offerDto.getAddress());
            offer.setPrice(offerDto.getPrice());
            offer.setPlaces(offerDto.getPlaces());
            offer.setRooms(offerDto.getRooms());
            offer.setRoomType(RoomType.valueOf(offerDto.getRoomType()));
            offer.setAvailability(offerDto.getAvailability());
            offer.setAvailableFrom(offerDto.getAvailableFrom());

            offerRepository.save(offer);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Offer updated successfully");
    }

    public ResponseEntity<Page<OfferDto>> getAllByCity(String city) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Offer> offers = offerRepository.findAllByCity(city, pageable);
        if(offers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(offerMapper.convertPageToPageDto(offers,OfferDto.class));
    }
}
