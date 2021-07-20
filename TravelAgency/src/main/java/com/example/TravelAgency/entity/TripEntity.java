package com.example.TravelAgency.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "trip")
public class TripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tripId;

    @Column(nullable = false, name = "trip_reason")
    private String tripReason;

    @Column(nullable = false, name = "trip_description")
    private String tripDescription;

    @Column(nullable = false, name = "from_place")
    private String fromPlace;

    @Column(nullable = false, name = "to_place")
    private String toPlace;

    @Column(nullable = false, name = "depature_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date departureDate;

    @Column(nullable = false, name = "arrival_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date arrivalDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "status")
    private String status;
}
