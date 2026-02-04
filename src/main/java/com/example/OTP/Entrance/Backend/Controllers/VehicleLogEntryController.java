package com.example.OTP.Entrance.Backend.Controllers;

import com.example.OTP.Entrance.Backend.Entities.VehicleLogEntry;
import com.example.OTP.Entrance.Backend.Repositories.VehicleLogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@RestController
@RequestMapping("/api/vehicle-log")
public class VehicleLogEntryController {

    @Autowired
    private VehicleLogEntryRepository repository;

    @PostMapping("/entry")
    public ResponseEntity<VehicleLogEntry> logEntry(@RequestBody VehicleLogEntry entry) {
        entry.setEntryDate(LocalDate.now());
        entry.setEntryTime(LocalTime.now());
        return ResponseEntity.ok(repository.save(entry));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<VehicleLogEntry> editExitAndRegistration(
            @PathVariable Long id,
            @RequestBody VehicleLogEntry updateData) {

        VehicleLogEntry entry = repository.findById(id).orElseThrow();

        if (updateData.getExitTime() != null) {
            entry.setExitTime(updateData.getExitTime());
        } else {
            entry.setExitTime(LocalTime.now());
        }

        if (updateData.getRegistrationNumber() != null) {
            entry.setRegistrationNumber(updateData.getRegistrationNumber());
        }

        return ResponseEntity.ok(repository.save(entry));
    }


    @GetMapping("/today")
    public List<VehicleLogEntry> getTodayLogs() {
        return repository.findByEntryDate(LocalDate.now());
    }
}
