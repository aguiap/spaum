package com.catolica.sc.spaum.services;

import com.catolica.sc.spaum.dto.TokenAnalysisAccessDTO;

import java.util.List;

public interface AnalysisServices {

    TokenAnalysisAccessDTO getAllAnalysisByToken(String token);
}
