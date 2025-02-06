package com.bank.authorization.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table (name = "audit")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Audit {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column (name = "entity_type", nullable = false, length = 40)
    private String entityType;

    @Column (name = "operation_type", nullable = false, length = 255)
    private String operationType;

    @Column (name = "created_by", nullable = false, length = 255)
    private String createdBy;

    @Column (name = "modified_by", length = 255)
    private String modifiedBy;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "modified_at")
    private OffsetDateTime modifiedAt;

    @Column(name = "new_entity_json")
    private String newEntityJson;

    @Column(name = "entity_json", nullable = false)
    private String entityJson;

    public Audit(String entityType, String operationType, String createdBy, String modifiedBy, OffsetDateTime createdAt, OffsetDateTime modifiedAt, String newEntityJson, String entityJson) {
        this.entityType = entityType;
        this.operationType = operationType;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.newEntityJson = newEntityJson;
        this.entityJson = entityJson;
    }
}
