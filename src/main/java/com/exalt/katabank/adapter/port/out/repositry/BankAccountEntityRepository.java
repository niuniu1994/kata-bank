package com.exalt.katabank.adapter.port.out.repositry;

import com.exalt.katabank.adapter.port.out.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kainingxin
 */
@Repository
public interface BankAccountEntityRepository extends JpaRepository<BankAccountEntity, Long> {
}
