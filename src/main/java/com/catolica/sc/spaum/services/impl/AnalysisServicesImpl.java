package com.catolica.sc.spaum.services.impl;

import com.catolica.sc.spaum.dto.AnalysisDTO;
import com.catolica.sc.spaum.dto.TokenAnalysisAccessDTO;
import com.catolica.sc.spaum.model.Analysis;
import com.catolica.sc.spaum.model.TokenAnalysisAccess;
import com.catolica.sc.spaum.repositories.AnalysisRepository;
import com.catolica.sc.spaum.repositories.TokenAnalysisAccessRepository;
import com.catolica.sc.spaum.services.AnalysisServices;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnalysisServicesImpl implements AnalysisServices {
    @Autowired
    TokenAnalysisAccessRepository tokenAnalysisAccessRepository;

    @Autowired
    AnalysisRepository analysisRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public TokenAnalysisAccessDTO getAllAnalysisByToken(String token) {
        Optional<TokenAnalysisAccess> tokenAnalysisAccess =  tokenAnalysisAccessRepository.findByToken(token);
        if(tokenAnalysisAccess.isPresent()){
            List<Analysis> analyses = analysisRepository.findByIdToken(tokenAnalysisAccess.get().getId());
            List<AnalysisDTO> analysisDTOS = analyses.stream()
                    .map(c -> mapper.map(c, AnalysisDTO.class))
                    .collect(Collectors.toList());
            return TokenAnalysisAccessDTO.builder()
                    .id(tokenAnalysisAccess.get().getId())
                    .token(token)
                    .path(tokenAnalysisAccess.get().getPath())
                    .course(tokenAnalysisAccess.get().getCourse())
                    .typeAnalyses(tokenAnalysisAccess.get().getTypeAnalyses())
                    .analyses(analysisDTOS)
                    .build();
        }
        return TokenAnalysisAccessDTO.builder()
                .token(token)
                .path("")
                .course("")
                .typeAnalyses("")
                .analyses(List.of())
                .build();
    }
}
