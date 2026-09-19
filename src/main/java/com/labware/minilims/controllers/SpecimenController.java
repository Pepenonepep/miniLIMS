package com.labware.minilims.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.labware.minilims.domain.entities.ResultAnalysis;
import com.labware.minilims.domain.entities.Specimen;
import com.labware.minilims.services.SpecimenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/specimens")
@RequiredArgsConstructor
public class SpecimenController {

    private final SpecimenService specimenService;

    @GetMapping
    public ResponseEntity<List<Specimen>> findAll() {
        List<Specimen> list = specimenService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Specimen> findById(@PathVariable Long id) {
        Specimen specimen = specimenService.findById(id);
        return ResponseEntity.ok().body(specimen);
    }

    @PostMapping
    public ResponseEntity<Specimen> insert(@RequestBody Specimen specimen) {
        specimen = specimenService.insert(specimen);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(specimen.getId())
                .toUri();
        return ResponseEntity.created(uri).body(specimen);
    }

    @PostMapping(value = "/{id}/results")
    public ResponseEntity<Specimen> addResult(@PathVariable Long id, @RequestBody ResultAnalysis result) {
        Specimen specimen = specimenService.addResult(
                id,
                result.getParameterAnalysis().getId(),
                result.getMeasuredValue());
        return ResponseEntity.ok().body(specimen);
    }
}