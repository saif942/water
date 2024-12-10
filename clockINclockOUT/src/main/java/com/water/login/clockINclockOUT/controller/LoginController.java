package com.water.login.clockINclockOUT.controller;

import com.water.login.clockINclockOUT.dto.UserClockInDTO;
import com.water.login.clockINclockOUT.dto.UserClockOutDTO;
import com.water.login.clockINclockOUT.service.ClockInService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userController")
public class LoginController {

    private final ClockInService clockInService;

    public LoginController(ClockInService clockInService) {
        this.clockInService = clockInService;
    }

    @PostMapping("/userClockIn")
    public ResponseEntity<String> userClockIn(@RequestBody UserClockInDTO userClockInDTO) {
        return clockInService.clockIn(userClockInDTO);
    }

    @PostMapping("/userClockOut")
    public ResponseEntity<String> userClockIn(@RequestBody UserClockOutDTO userClockOutDTO) {
        return clockInService.clockOut(userClockOutDTO);
    }

    @PostMapping("/userStatus/{userID}")
    public ResponseEntity<String> userStatus(@PathVariable int userID) {
        return clockInService.checkStatus(userID);
    }
}
