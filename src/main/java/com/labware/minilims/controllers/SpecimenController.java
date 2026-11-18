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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Specimens", description = "Endpoints para gerenciamento e ciclo de vida de amostras laboratoriais")
@RestController
@RequestMapping(value = "/specimens")
@RequiredArgsConstructor
public class SpecimenController {

    private final SpecimenService specimenService;

    @Operation(summary = "Listar todas as amostras", description = "Retorna uma lista com todas as amostras registradas no sistema")
    @ApiResponse(responseCode = "200", description = "Lista de amostras recuperada com sucesso")
    @GetMapping
    public ResponseEntity<List<Specimen>> findAll() {
        List<Specimen> list = specimenService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Buscar amostra por ID", description = "Retorna os detalhes de uma amostra específica com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Amostra encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Amostra não encontrada")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Specimen> findById(@PathVariable Long id) {
        Specimen specimen = specimenService.findById(id);
        return ResponseEntity.ok().body(specimen);
    }

    @Operation(summary = "Cadastrar nova amostra", description = "Cria um novo registro de amostra laboratorial no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Amostra criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<Specimen> insert(@RequestBody Specimen specimen) {
        specimen = specimenService.insert(specimen);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(specimen.getId())
                .toUri();
        return ResponseEntity.created(uri).body(specimen);
    }

    @Operation(summary = "Adicionar resultado de análise", description = "Associa o resultado de um parâmetro de análise a uma amostra existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado registrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Amostra ou parâmetro de análise não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados do resultado inválidos")
    })
    @PostMapping(value = "/{id}/results")
    public ResponseEntity<Specimen> addResult(@PathVariable Long id, @RequestBody ResultAnalysis result) {
        Specimen specimen = specimenService.addResult(
                id,
                result.getParameterAnalysis().getId(),
                result.getMeasuredValue());
        return ResponseEntity.ok().body(specimen);
    }
}