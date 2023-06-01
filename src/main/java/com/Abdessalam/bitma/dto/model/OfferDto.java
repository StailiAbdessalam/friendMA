package com.Abdessalam.friendMA.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.Abdessalam.friendMA.entity.OfferImage;
import com.Abdessalam.friendMA.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OfferDto {
    private Long id;
    private String type;
    private String title;
    private List<OfferImage> images;
    private User user;
    private String description;
    private String city;
    private String address;
    private Double price;
    private int places;
    private int rooms;
    private String roomType;
    private Boolean availability;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate availableFrom;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate;
}
