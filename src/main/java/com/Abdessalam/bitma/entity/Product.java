package com.Abdessalam.friendMA.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String type;
    private String city;
    private Double price;
    private boolean availability;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime available_from;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_date;

}
