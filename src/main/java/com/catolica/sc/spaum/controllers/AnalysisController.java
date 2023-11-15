package com.catolica.sc.spaum.controllers;

import com.catolica.sc.spaum.dto.TokenAnalysisAccessDTO;
import com.catolica.sc.spaum.services.AnalysisServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Analysis Endpoint")
@RestController
@RequestMapping("/token")
public class AnalysisController {

    @Autowired
    AnalysisServices analysisServices;

    @Operation(summary = "Returns all analytics by token")
    @GetMapping(value = "/v1/analysis/get-all-analysis-by-token/{token}")
    public ResponseEntity<TokenAnalysisAccessDTO> getAllAnalysisByToken(@PathVariable String token){
        return ResponseEntity.ok(analysisServices.getAllAnalysisByToken(token));
    }
}
