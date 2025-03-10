package com.atabey.banking_app.service;

import com.atabey.banking_app.dto.AccountDto;
import com.atabey.banking_app.dto.AccountDtoConverter;
import com.atabey.banking_app.model.Account;
import com.atabey.banking_app.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountDtoConverter accountDtoConverter;

    public AccountDto createAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountHolderName(accountDto.getAccountHolderNameDto());
        account.setBalance(accountDto.getBalanceDto());
        return accountDtoConverter.convert(accountRepository.save(account));
    }

    public List<AccountDto> getAllAccounts() {

     return accountRepository.findAll()
             .stream()
             .map(accountDtoConverter::convert)
             .toList();
    }

    public AccountDto getByIdAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account could not find by id: " + id));

        return accountDtoConverter.convert(account);
    }

    public AccountDto updateAccount(Long id, AccountDto accountDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account could not find by id: " + id));

        account.setAccountHolderName(accountDto.getAccountHolderNameDto());
        account.setBalance(accountDto.getBalanceDto());

        return accountDtoConverter.convert(accountRepository.save(account));
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account could not find by id: " + id));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        return accountDtoConverter.convert(accountRepository.save(account));
    }

    public AccountDto withdraw(Long id, double amount) {

        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account could not find by id: " + id));

        if (account.getBalance() - amount < 0){
            throw new RuntimeException("Insufficient balance");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        return accountDtoConverter.convert( accountRepository.save(account));

    }

}
