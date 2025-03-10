package com.atabey.banking_app.controller;

import com.atabey.banking_app.dto.AccountDto;
import com.atabey.banking_app.dto.TransferFundDto;
import com.atabey.banking_app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/account/")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/createAccount")
    public ResponseEntity<AccountDto> creaateAccount(@RequestBody AccountDto accountDto) {

        return new ResponseEntity<>
                (accountService.createAccount(accountDto)
                        , HttpStatus.CREATED);
    }

    @GetMapping("/getAllAccounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/getByIdAccount/{id}")
    public ResponseEntity<AccountDto> getByIdAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getByIdAccount(id));
    }

    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.updateAccount(id, accountDto));
    }

    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted Successfully");
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                              @RequestBody double amount){

    return ResponseEntity.ok(accountService.deposit(id, amount));
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw (
            @PathVariable Long id, @RequestBody double amount){
        return ResponseEntity.ok(accountService.withdraw(id,amount));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFund(@RequestBody TransferFundDto transferFundDto){
        accountService.transferFunds(transferFundDto);

        return ResponseEntity.ok("Transfer Successfull");
    }



}
