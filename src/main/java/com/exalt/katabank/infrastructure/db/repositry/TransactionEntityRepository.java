package com.exalt.katabank.infrastructure.db.repositry;

import com.exalt.katabank.infrastructure.db.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kainingxin
 */
@Repository
public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Long > {
}
