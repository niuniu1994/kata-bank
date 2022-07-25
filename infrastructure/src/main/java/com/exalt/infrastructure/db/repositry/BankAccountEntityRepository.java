package com.exalt.infrastructure.db.repositry;

import com.exalt.infrastructure.db.model.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author kainingxin
 */
@Repository
public interface BankAccountEntityRepository extends JpaRepository<BankAccountEntity, Long> {

    @Query("SELECT ba FROM BankAccountEntity ba JOIN FETCH ba.transactionEntitySet WHERE ba.accountId = :accountId")
    Optional<BankAccountEntity> findByAccountIdWithTransactions(@Param("accountId") Long accountId);

}
