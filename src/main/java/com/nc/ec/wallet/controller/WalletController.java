package com.nc.ec.wallet.controller;

import com.nc.ec.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    WalletService walletService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getWalletBalance() {
        return new ResponseEntity<>("Balance on wallet: " + walletService.getBalance(), HttpStatus.OK);
    }

    @RequestMapping(value = "/cash/{count}", method = RequestMethod.PUT)
    public ResponseEntity<String> putCashToWallet(@PathVariable Integer count) {
        int balanceBeforeIncrease = walletService.getBalance();
        walletService.increaseBalance(count);
        String response = "There are balance on wallet before top up: " + balanceBeforeIncrease + '\n' +
                "There are balance on wallet after increase: " + walletService.getBalance();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/cash/{count}", method = RequestMethod.GET)
    public ResponseEntity<String> getCashFromWallet(@PathVariable Integer count) {
        int balanceBeforeDecrease = walletService.getBalance();
        walletService.decreaseBalance(count);
        if (balanceBeforeDecrease == walletService.getBalance()) {
            return new ResponseEntity<>("There are insufficient funds on wallet \n Balance: " + walletService.getBalance(), HttpStatus.CONFLICT);
        }
        String response = "There are balance on wallet before withdraw: " + walletService.getBalance() + '\n' +
                "There are balance on wallet after withdraw: " + walletService.getBalance();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
