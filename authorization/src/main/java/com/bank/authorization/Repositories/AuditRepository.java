package com.bank.authorization.Repositories;

import com.bank.authorization.Entities.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository <Audit, Long> {

}
