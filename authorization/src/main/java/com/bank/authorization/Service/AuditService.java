package com.bank.authorization.Service;

import com.bank.authorization.DTO.AuditDTO;
import com.bank.authorization.Entity.Audit;
import com.bank.authorization.Mapper.AuditMapper;
import com.bank.authorization.Repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditService {

    private final AuditRepository auditRepository;

    @Autowired
    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Transactional
    public List<AuditDTO> getAllAudits() {
        return auditRepository.findAll().stream()
                .map(AuditMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AuditDTO getAuditById(Long id) {
        return auditRepository.findById(id)
                .map(AuditMapper.INSTANCE::toDTO)
                .orElse(null);
    }

    @Transactional
    public AuditDTO createAudit(AuditDTO auditDTO) {
        Audit audit = AuditMapper.INSTANCE.toEntity(auditDTO);
        Audit savedAudit = auditRepository.save(audit);
        return AuditMapper.INSTANCE.toDTO(savedAudit);
    }

    @Transactional
    public AuditDTO updateAudit(Long id, AuditDTO auditDTO) {
        return auditRepository.findById(id)
                .map(audit -> {
                    audit.setEntityType(auditDTO.getEntityType());
                    audit.setOperationType(auditDTO.getOperationType());
                    audit.setCreatedBy(auditDTO.getCreatedBy());
                    audit.setModifiedBy(auditDTO.getModifiedBy());
                    audit.setCreatedAt(auditDTO.getCreatedAt());
                    audit.setModifiedAt(auditDTO.getModifiedAt());
                    audit.setNewEntityJson(auditDTO.getNewEntityJson());
                    audit.setEntityJson(auditDTO.getEntityJson());
                    Audit updatedAudit = auditRepository.save(audit);
                    return AuditMapper.INSTANCE.toDTO(updatedAudit);
                })
                .orElse(null);
    }

    @Transactional
    public void deleteAudit(Long id) {
        auditRepository.deleteById(id);
    }
}
