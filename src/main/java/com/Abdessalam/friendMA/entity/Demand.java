package com.Abdessalam.friendMA.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.Abdessalam.friendMA.enumeration.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userId;

    private String type = "demand";
    private String title;
    private String description;
    private String city;
    private Double budget;
    private RoomType roomType;

    @Nullable
    private Boolean availability;

    @Nullable
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate availableFrom;

    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime lastUpdate;

}
