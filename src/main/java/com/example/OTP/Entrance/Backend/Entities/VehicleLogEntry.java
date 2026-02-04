package com.example.OTP.Entrance.Backend.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "vehicle_log_entries")
@Getter
@Setter
public class VehicleLogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String initial;
    private String surname;
    private String extension;
    private String registrationNumber;
    private String idNumber;

    private LocalDate entryDate;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private String signature;

}
