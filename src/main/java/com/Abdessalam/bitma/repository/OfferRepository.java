package com.Abdessalam.friendMA.repository;

import com.Abdessalam.friendMA.entity.Offer;
import lombok.experimental.NonFinal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Override
    @Nonnull
    Page<Offer> findAll(@Nullable Pageable pageable);

    List<Offer> findAllByUserId(Long id);

    Page<Offer> findAllByCity(String city, Pageable pageable);
}
