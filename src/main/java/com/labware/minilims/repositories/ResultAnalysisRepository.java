package com.labware.minilims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labware.minilims.domain.entities.ResultAnalysis;

@Repository
public interface ResultAnalysisRepository extends JpaRepository<ResultAnalysis, Long> {

}
