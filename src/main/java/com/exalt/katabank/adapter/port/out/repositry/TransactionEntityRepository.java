package com.exalt.katabank.adapter.port.out.repositry;

import com.exalt.katabank.adapter.port.out.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Long > {
}
