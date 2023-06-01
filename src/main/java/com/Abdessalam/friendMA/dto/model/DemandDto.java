package com.Abdessalam.friendMA.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.Abdessalam.friendMA.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DemandDto {
    private long id;
    private User user;
    private String type;
    private String title;
    private String description;
    private String city;
    private Double budget;
    private String roomType;
    private Boolean availability;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate availableFrom;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate;

}
