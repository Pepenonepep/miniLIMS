package com.labware.minilims.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.labware.minilims.domain.entities.ParameterAnalysis;
import com.labware.minilims.repositories.ParameterAnalysisRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final ParameterAnalysisRepository parameterAnalysisRepository;

    @Override
    public void run(String... args) throws Exception {

        ParameterAnalysis p1 = new ParameterAnalysis(null, "pH", "pH", 6.5, 7.5);
        ParameterAnalysis p2 = new ParameterAnalysis(null, "Turbidez", "NTU", 0.0, 5.0);
        ParameterAnalysis p3 = new ParameterAnalysis(null, "Cloro Residual", "mg/L", 0.2, 2.0);

        parameterAnalysisRepository.saveAll(Arrays.asList(p1, p2, p3));
    }
}