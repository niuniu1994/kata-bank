package com.exalt.application.controller;

import com.exalt.application.mapper.TransactionMapper;
import com.exalt.application.model.CustomResponse;
import com.exalt.domain.model.Money;
import com.exalt.domain.model.Transaction;
import com.exalt.domain.service.BankAccountService;
import org.springframework.lang.NonNull;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * @author kainingxin
 */

@RestController
@RequestMapping("/api/account")
@Validated
public class AccountController {

    private final BankAccountService bankAccountService;

    private final TransactionTemplate transactionTemplate;

    public AccountController(BankAccountService bankAccountService, PlatformTransactionManager transactionManager) {
        this.bankAccountService = bankAccountService;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @GetMapping("/{id}/history")
    public CustomResponse getTransactions(@PathVariable @NonNull Long id){
        Set<Transaction> transactionSet = bankAccountService.checkTransactions(id);
        return CustomResponse.success(TransactionMapper.transactionSet2TransactionDTOSet(transactionSet));
    }

    @PostMapping ("/{id}/money/de/{money}")
    public CustomResponse depositMoney(@PathVariable @NonNull Long id,@PathVariable @NonNull String money){
        Boolean res = false;
        synchronized (this){
             res = transactionTemplate.execute(status ->
                    bankAccountService.depositMoney(id,new Money(money)));
        }
        return Boolean.TRUE.equals(res) ? CustomResponse.success(null): CustomResponse.error();
    }

    @PostMapping("/{id}/money/re/{money}")
    @Transactional
    public CustomResponse retrieveMoney(@PathVariable @NonNull Long id,@PathVariable @NonNull String money){
        Boolean res = false;
        synchronized (this){
            res = transactionTemplate.execute( status ->
                            bankAccountService.retrieveMoney(id,new Money(money)));
        }
        return Boolean.TRUE.equals(res) ? CustomResponse.success(null): CustomResponse.error();
    }
}
