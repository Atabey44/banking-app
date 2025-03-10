package com.atabey.banking_app.dto;

import com.atabey.banking_app.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {

    public AccountDto convert(Account account){
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountHolderNameDto(account.getAccountHolderName());
        accountDto.setBalanceDto(account.getBalance());
        return accountDto;
    }
}
