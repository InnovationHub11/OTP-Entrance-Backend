package com.example.OTP.Entrance.Backend.Controllers;

import com.example.OTP.Entrance.Backend.Repositories.UserRepository;
import com.example.OTP.Entrance.Backend.Services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Long idNumber = Long.valueOf(request.get("idNumber"));
        String password = request.get("password");

        return userRepository.findByIdNumber(idNumber)
                .map(user -> {
                    if (user.getPassword().equals(password)) {
                        // ✅ Always generate QR code from regNumber
                        String qrCodeBase64 = otpService.generateQrCodeForUser(user.getRegNumber());

                        return ResponseEntity.ok(Map.of(
                                "success", true,
                                "message", "Login successful",
                                "role", user.getRole(),
                                "name", user.getName(),
                                "regNumber", user.getRegNumber(),
                                "qrCode", qrCodeBase64
                        ));
                    } else {
                        return ResponseEntity.badRequest().body(Map.of(
                                "success", false,
                                "message", "Invalid password"
                        ));
                    }
                })
                .orElse(ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "User not found"
                )));
    }
}
