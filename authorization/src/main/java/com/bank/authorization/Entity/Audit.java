package com.bank.authorization.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "audit")
@Getter
@Setter
@NoArgsConstructor
public class Audit {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "entity_type")
    private String entityType;

    @Column (name = "operation_type")
    private String operationType;

    @Column (name = "created_by")
    private String createdBy;

    @Column (name = "modified_by", nullable = false)
    private String modifiedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "new_entity_json", nullable = false)
    private String newEntityJson;

    @Column(name = "entity_json")
    private String entityJson;

    public Audit(String entityType, String operationType, String createdBy, String modifiedBy, LocalDateTime createdAt, LocalDateTime modifiedAt, String newEntityJson, String entityJson) {
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
