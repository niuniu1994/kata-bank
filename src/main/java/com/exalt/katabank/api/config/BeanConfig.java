package com.exalt.katabank.api.config;

import com.exalt.katabank.domain.port.server_side.AccountPersistencePort;
import com.exalt.katabank.domain.service.BankAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kainingxin
 */
@Configuration
public class BeanConfig {

    @Bean
    public BankAccountService createBankAccountService(AccountPersistencePort accountPersistencePort){
        return new BankAccountService(accountPersistencePort);
    }

}
