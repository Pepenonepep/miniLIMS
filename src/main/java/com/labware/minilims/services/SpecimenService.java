package com.labware.minilims.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.labware.minilims.domain.entities.AuditLog;
import com.labware.minilims.domain.entities.ParameterAnalysis;
import com.labware.minilims.domain.entities.ResultAnalysis;
import com.labware.minilims.domain.entities.Specimen;
import com.labware.minilims.domain.enums.Status;
import com.labware.minilims.repositories.AuditLogRepository;
import com.labware.minilims.repositories.ParameterAnalysisRepository;
import com.labware.minilims.repositories.ResultAnalysisRepository;
import com.labware.minilims.repositories.SpecimenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpecimenService {

    private final SpecimenRepository specimenRepository;
    private final ResultAnalysisRepository resultAnalysisRepository;
    private final ParameterAnalysisRepository parameterAnalysisRepository;
    private final AuditLogRepository auditLogRepository;

    public List<Specimen> findAll() {
        return specimenRepository.findAll();
    }

    public Specimen findById(Long id) {
        return specimenRepository.findById(id).orElseThrow(() -> new RuntimeException("Specimen not found"));
    }

    @Transactional
    public Specimen insert(Specimen specimen) {
        specimen.setId(null);
        if (specimen.getStatusSpecimen() == null) {
            specimen.setStatusSpecimen(Status.RECEIVED);
        }
        specimen = specimenRepository.save(specimen);

        AuditLog log = new AuditLog(null, "Specimen", specimen.getId(), "CREATE",
                "Amostra criada com código " + specimen.getTrackingCode(), "SYSTEM", LocalDateTime.now());
        auditLogRepository.save(log);

        return specimen;
    }

    @Transactional
    public Specimen addResult(Long specimenId, Long parameterId, Double measuredValue) {
        Specimen specimen = specimenRepository.findById(specimenId)
                .orElseThrow(() -> new RuntimeException("Specimen not found"));

        ParameterAnalysis parameter = parameterAnalysisRepository.findById(parameterId)
                .orElseThrow(() -> new RuntimeException("Parameter not found"));

        ResultAnalysis result = new ResultAnalysis();
        result.setParameterAnalysis(parameter);
        result.setMeasuredValue(measuredValue);
        result.setDateAnalyzed(LocalDateTime.now());
        result.setSpecimen(specimen);

        result.validateConformity();

        result = resultAnalysisRepository.save(result);

        specimen.addResult(result);
        specimen = specimenRepository.save(specimen);

        AuditLog log = new AuditLog(null, "Specimen", specimen.getId(), "ADD_RESULT",
                "Resultado de " + parameter.getName() + " adicionado. Valor: " + measuredValue, "SYSTEM",
                LocalDateTime.now());
        auditLogRepository.save(log);

        return specimen;
    }
}