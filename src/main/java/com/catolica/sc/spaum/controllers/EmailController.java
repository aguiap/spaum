package com.catolica.sc.spaum.controllers;

import com.catolica.sc.spaum.services.EmailServices;
import com.catolica.sc.spaum.vo.EmailDataVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Email Endpoint")
@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailServices emailServices;

    @Operation(summary = "Send Email")
    @PostMapping(value = "/v1/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailDataVo emailDataVo, HttpServletRequest request) throws Exception {
        String origin = request.getHeader(HttpHeaders.ORIGIN);
        emailServices.sendEmail(emailDataVo, origin);
        return ResponseEntity.ok().build();
    }
}
