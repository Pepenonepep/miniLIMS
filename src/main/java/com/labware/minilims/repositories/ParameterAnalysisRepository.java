package com.labware.minilims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labware.minilims.domain.entities.ParameterAnalysis;

@Repository
public interface ParameterAnalysisRepository extends JpaRepository<ParameterAnalysis, Long> {

}
