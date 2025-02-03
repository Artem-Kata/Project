package com.bank.authorization.Controllers;

import com.bank.authorization.DTO.AuditDTO;
import com.bank.authorization.Service.AuditService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audits")
public class AuditController {
    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    public List<AuditDTO> getAllAudits() {
        return auditService.getAllAudits();
    }

    @GetMapping("/{id}")
    public AuditDTO getAuditById(@PathVariable Long id) {
        return auditService.getAuditById(id);
    }

    @PostMapping
    public AuditDTO createAudit(@RequestBody AuditDTO auditDTO) {
        return auditService.createAudit(auditDTO);
    }

    @PutMapping("/{id}")
    public AuditDTO updateAudit(@PathVariable Long id, @RequestBody AuditDTO auditDTO) {
        return auditService.updateAudit(id, auditDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAudit(@PathVariable Long id) {
        auditService.deleteAudit(id);
    }
}
