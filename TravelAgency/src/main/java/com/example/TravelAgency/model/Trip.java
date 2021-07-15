package com.example.TravelAgency.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long tripId;

    @Column(nullable = false, name = "trip_reason")
    @Getter @Setter
    private String tripReason;

    @Column(nullable = false, name = "trip_description")
    @Getter @Setter
    private String tripDescription;

    @Column(nullable = false, name = "from_place")
    @Getter @Setter
    private String fromPlace;

    @Column(nullable = false, name = "to_place")
    @Getter @Setter
    private String toPlace;

    @Column(nullable = false, name = "depature_date")
    @Getter @Setter
    private Date departureDate;

    @Column(nullable = false, name = "arrival_date")
    @Getter @Setter
    private Date arrivalDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @Getter @Setter
    private User user;

    @JoinColumn(name = "status")
    @Getter @Setter
    private String status;
}
