package com.nc.ec.wallet.service;

import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private int balance = 0;

    public void increaseBalance(int count) {
        balance += count;
    }

    public void decreaseBalance(int count) {
        if (count <= balance) {
            balance -= count;
        }
    }

    public int getBalance() {
        return balance;
    }
}
