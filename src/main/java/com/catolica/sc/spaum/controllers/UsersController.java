package com.catolica.sc.spaum.controllers;

import com.catolica.sc.spaum.services.UsersServices;
import com.catolica.sc.spaum.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users Endpoint")
@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    UsersServices usersServices;

    @Operation(summary = "Authenticates a user and return a token")
    @PatchMapping(value = "/v1/user/change-password")
    public ResponseEntity<?> changePassword(@RequestBody UserVo userVo){
        usersServices.changePassword(userVo);
        return ResponseEntity.ok().build();
    }
}
