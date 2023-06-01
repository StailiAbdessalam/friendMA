package com.Abdessalam.friendMA.controller;

import com.Abdessalam.friendMA.dto.model.FavoriteDto;
import com.Abdessalam.friendMA.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteDto>> getAllFavoritesByUserId(@PathVariable long userId) {
        return favoriteService.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<String> addToFavorites(@RequestBody FavoriteDto favorite) {
        return favoriteService.save(favorite);
    }
}
