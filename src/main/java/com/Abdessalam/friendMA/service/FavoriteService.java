package com.Abdessalam.friendMA.service;

import com.Abdessalam.friendMA.dto.mapperInterface.IMapperDto;
import com.Abdessalam.friendMA.dto.model.FavoriteDto;
import com.Abdessalam.friendMA.entity.Favorite;
import com.Abdessalam.friendMA.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final IMapperDto<Favorite, FavoriteDto> favoriteMapper;

    public ResponseEntity<String> save(FavoriteDto favoriteDto) {
        Favorite favorite = favoriteMapper.convertToEntity(favoriteDto, Favorite.class);
        log.info("Saving favorite" + favorite.getUser());
        favoriteRepository.save(favorite);
        return ResponseEntity.ok("Added to favorites");
    }

    public ResponseEntity<List<FavoriteDto>> findByUserId(long userId) {
        log.info("Retrieving favorites by userId: {}", userId);
        return ResponseEntity.ok(favoriteMapper.convertListToListDto(favoriteRepository.findByUserId(userId), FavoriteDto.class));
    }
}
