package com.exalt.infrastructure.db.repositry;

import com.exalt.infrastructure.db.model.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kainingxin
 */
@Repository
public interface BankAccountEntityRepository extends JpaRepository<BankAccountEntity, Long> {
}
