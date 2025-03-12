package com.atabey.banking_app.service;

import com.atabey.banking_app.dto.AccountDto;
import com.atabey.banking_app.dto.AccountDtoConverter;
import com.atabey.banking_app.dto.TransferFundDto;
import com.atabey.banking_app.exception.AccountException;
import com.atabey.banking_app.model.Account;
import com.atabey.banking_app.model.Transaction;
import com.atabey.banking_app.repository.AccountRepository;
import com.atabey.banking_app.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountDtoConverter accountDtoConverter;
    private final TransactionRepository transactionRepository;

    private static  final String TRANSACTION_TYPE_DEPOSIT="deposit";

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
                .orElseThrow(()-> new AccountException("Account could not find by id: " + id));

        return accountDtoConverter.convert(account);
    }

    public AccountDto updateAccount(Long id, AccountDto accountDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new AccountException("Account could not find by id: " + id));

        account.setAccountHolderName(accountDto.getAccountHolderNameDto());
        account.setBalance(accountDto.getBalanceDto());

        return accountDtoConverter.convert(accountRepository.save(account));
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }


    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new AccountException("Account could not find by id: " + id));

        double total = account.getBalance() + amount;
        account.setBalance(total);

        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType("DEPOSIT");
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
        return accountDtoConverter.convert(accountRepository.save(account));
    }

    public AccountDto withdraw(Long id, double amount) {

        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new AccountException("Account could not find by id: " + id));

        if (account.getBalance() - amount < 0){
            throw new RuntimeException("Insufficient balance");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        return accountDtoConverter.convert( accountRepository.save(account));

    }

    public void transferFunds(TransferFundDto transferFundDto){
        Account fromAccount = accountRepository.findById(transferFundDto.fromAccountId())
                .orElseThrow(()-> new AccountException("Account could not find by id:"));

        Account toAccount = accountRepository.findById(transferFundDto.toAccountId())
                .orElseThrow(()-> new AccountException("Account could not find by id:"));

    fromAccount.setBalance(fromAccount.getBalance()-transferFundDto.amount());
    toAccount.setBalance(toAccount.getBalance()+ transferFundDto.amount());
    accountRepository.save(fromAccount);
    accountRepository.save(toAccount);

    }


}
