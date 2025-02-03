package com.bank.authorization.Repository;

import com.bank.authorization.Entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<Audit, Long> {
}
