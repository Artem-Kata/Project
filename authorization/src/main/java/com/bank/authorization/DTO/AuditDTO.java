package com.bank.authorization.DTO;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AuditDTO {

    private Long id;
    private String entityType;
    private String createdBy;
    private String modifiedBy;
    private OffsetDateTime createdAt;
    private OffsetDateTime modifiedAt;
    private String newEntityJson;
    private String entityJson;
}
