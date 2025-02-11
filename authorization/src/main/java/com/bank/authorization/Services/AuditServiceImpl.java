package com.bank.authorization.Services;

import com.bank.authorization.DTO.AuditDTO;
import com.bank.authorization.Entities.Audit;
import com.bank.authorization.Exceptions.ResourceNotFoundException;
import com.bank.authorization.Mapper.AuditMapper;
import com.bank.authorization.Repositories.AuditRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Slf4j
@Service
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    @Transactional
    public AuditDTO createAudit(AuditDTO auditDTO) {
        Audit audit = AuditMapper.INSTANCE.toEntity(auditDTO);
        Audit savedAudit = auditRepository.save(audit);
        log.info("Audit created for entity type: {}", savedAudit.getEntityType());
        return AuditMapper.INSTANCE.toDTO(savedAudit);
    }

    @Override
    public AuditDTO updateAudit(Long id, AuditDTO auditDTO) {
        Audit existingAudit = auditRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Audit not found with id: " + id));

        existingAudit.setEntityType(auditDTO.getEntityType());
        existingAudit.setCreatedBy(auditDTO.getCreatedBy());
        existingAudit.setModifiedBy(auditDTO.getModifiedBy());
        existingAudit.setCreatedAt(auditDTO.getCreatedAt());
        existingAudit.setModifiedAt(auditDTO.getModifiedAt());
        existingAudit.setNewEntityJson(auditDTO.getNewEntityJson());
        existingAudit.setEntityJson(auditDTO.getEntityJson());

        Audit updatedAudit = auditRepository.save(existingAudit);
        log.info("Audit with id {} updated successfully.", id);

        return AuditMapper.INSTANCE.toDTO(updatedAudit);
    }
}
