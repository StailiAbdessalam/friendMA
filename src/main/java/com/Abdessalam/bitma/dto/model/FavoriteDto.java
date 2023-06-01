package com.Abdessalam.friendMA.dto.model;

import com.Abdessalam.friendMA.entity.Offer;
import com.Abdessalam.friendMA.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoriteDto {

    private Long id;
    private User user;
    private Offer offer;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdate;

}
