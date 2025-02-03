package com.bank.authorization.Mapper;

import com.bank.authorization.DTO.AuditDTO;
import com.bank.authorization.Entity.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuditMapper {

    AuditMapper INSTANCE = Mappers.getMapper(AuditMapper.class);
    AuditDTO toDTO(Audit audit);
    Audit toEntity(AuditDTO auditDTO);

}
