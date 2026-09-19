package com.labware.minilims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labware.minilims.domain.entities.Specimen;

@Repository
public interface SpecimenRepository extends JpaRepository<Specimen, Long> {

}
