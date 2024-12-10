package com.water.login.clockINclockOUT.controller;

import com.water.login.clockINclockOUT.dto.UserModelDto;
import com.water.login.clockINclockOUT.service.ClockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class TokenController {

    @Autowired
    private ClockInService clockInService;

    @GetMapping("/v1/getToken")
    public ResponseEntity<String> getToken(@RequestBody UserModelDto userModelDto){
        return clockInService.generateToken(userModelDto);
    }
}
