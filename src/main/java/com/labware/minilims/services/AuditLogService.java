package com.labware.minilims.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.labware.minilims.domain.entities.AuditLog;
import com.labware.minilims.repositories.AuditLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;

    public List<AuditLog> findAll() {
        return auditLogRepository.findAll();
    }

    public AuditLog log(String entity, Long entityId, String action, String details) {
        AuditLog log = new AuditLog(
                null,
                entity,
                entityId,
                action,
                details,
                "SYSTEM",
                LocalDateTime.now());
        return auditLogRepository.save(log);
    }

}
