package com.labware.minilims.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labware.minilims.services.AuditLogService;
import com.labware.minilims.domain.entities.AuditLog;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {
    private final AuditLogService auditLogService;

    @GetMapping
    public ResponseEntity<List<AuditLog>> findAll() {
        List<AuditLog> list = auditLogService.findAll();
        return ResponseEntity.ok().body(list);
    }
}
