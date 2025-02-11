package com.bank.authorization.Services;

import com.bank.authorization.DTO.AuditDTO;

public interface AuditService {

    AuditDTO createAudit(AuditDTO auditDTO);
    AuditDTO updateAudit(Long id, AuditDTO auditDTO);
}
