package com.bank.authorization.Validator;

import com.bank.authorization.DTO.AuditDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AuditDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AuditDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuditDTO auditDTO = (AuditDTO) target;

        if (auditDTO.getEntityType() == null || auditDTO.getEntityType().isEmpty()) {
            errors.rejectValue("entityType", "entityType.empty", "Entity type не должен быть пустым");
        }

        if (auditDTO.getOperationType() == null || auditDTO.getOperationType().isEmpty()) {
            errors.rejectValue("operationType", "operationType.empty", "Operation type не должен быть пустым");
        }

        if (auditDTO.getCreatedBy() == null || auditDTO.getCreatedBy().isEmpty()) {
            errors.rejectValue("createdBy", "createdBy.empty", "Created by не должен быть пустым");
        }

        if (auditDTO.getModifiedBy() == null || auditDTO.getModifiedBy().isEmpty()) {
            errors.rejectValue("modifiedBy", "modifiedBy.empty", "Modified by не должен быть пустым");
        }

        if (auditDTO.getCreatedAt() == null) {
            errors.rejectValue("createdAt", "createdAt.null", "Created at не должен быть пустым");
        }

        if (auditDTO.getModifiedAt() == null) {
            errors.rejectValue("modifiedAt", "modifiedAt.null", "Modified at не должен быть пустым");
        }
    }
}
