package com.Abdessalam.friendMA.repository;

import com.Abdessalam.friendMA.entity.Demand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Long>{
    @Override
    @Nonnull
    Page<Demand> findAll(@Nullable Pageable pageable);

    List<Demand> findAllByUserId(Long userId);

    Page<Demand> findAllByCity(String city, Pageable pageable);
}
