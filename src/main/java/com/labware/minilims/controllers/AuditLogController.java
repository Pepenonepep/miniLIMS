package com.labware.minilims.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labware.minilims.services.AuditLogService;
import com.labware.minilims.domain.entities.AuditLog;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@Tag(name = "Audit Logs", description = "Endpoints para consulta da trilha de auditoria (Audit Trail)")
@RestController
@RequestMapping(value = "/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {
    private final AuditLogService auditLogService;

    @Operation(summary = "Listar todos os logs de auditoria", description = "Retorna o histórico completo de alterações e eventos registados no sistema")
    @ApiResponse(responseCode = "200", description = "Lista de registos de auditoria recuperada com sucesso")
    @GetMapping
    public ResponseEntity<List<AuditLog>> findAll() {
        List<AuditLog> list = auditLogService.findAll();
        return ResponseEntity.ok().body(list);
    }
}
