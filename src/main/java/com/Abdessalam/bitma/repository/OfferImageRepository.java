package com.Abdessalam.friendMA.repository;

import com.Abdessalam.friendMA.entity.OfferImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
@Repository
public interface OfferImageRepository extends JpaRepository<OfferImage,Long> {
    List<OfferImage> findAllByOfferId(Long offerId);
}
