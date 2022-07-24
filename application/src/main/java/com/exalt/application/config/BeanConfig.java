package com.exalt.application.config;

import com.exalt.domain.port.driven.AccountPersistencePort;
import com.exalt.domain.service.BankAccountService;
import com.exalt.infrastructure.db.adapter.AccountPersistenceAdapter;
import com.exalt.infrastructure.db.repositry.BankAccountEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kainingxin
 */
@Configuration
public class BeanConfig {

    @Bean
    public BankAccountService bankAccountService(AccountPersistencePort accountPersistencePort){
        return new BankAccountService(accountPersistencePort);
    }

    @Bean
    public AccountPersistencePort accountPersistencePort(BankAccountEntityRepository bankAccountEntityRepository){
        return new AccountPersistenceAdapter(bankAccountEntityRepository);
    }



}
