package com.exalt.katabank.adapter.port.in;

import com.exalt.katabank.adapter.port.in.dto.CustomResponse;
import com.exalt.katabank.application.port.service.BankAccountService;
import com.exalt.katabank.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author kainingxin
 */

@RestController
@RequestMapping("/api/account")
@Validated
public class AccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public AccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }


    @GetMapping("/{id}/history")
    public CustomResponse<Set<Transaction>> getTransactions(@PathVariable @NonNull Long id){
        return new CustomResponse<>(200,"success",bankAccountService.checkTransactions(id));
    }

    @PostMapping ("/{id}/history")
    public CustomResponse<Set<Transaction>> getTransactions(@PathVariable @NonNull Long id){
        return new CustomResponse<>(200,"success",bankAccountService.checkTransactions(id));
    }

    @PostMapping("/{id}/history")
    public CustomResponse<Set<Transaction>> getTransactions(@PathVariable @NonNull Long id){
        return new CustomResponse<>(200,"success",bankAccountService.checkTransactions(id));
    }
}
