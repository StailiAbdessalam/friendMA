package com.Abdessalam.friendMA.repository;

import com.Abdessalam.friendMA.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Collection<Favorite> findByUserId(long userId);
}
