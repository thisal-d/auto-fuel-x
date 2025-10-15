package com.example.autofuelx.strategy;

public class PayPalPaymentStrategy implements PaymentStrategy {

    private final String accountEmail;

    public PayPalPaymentStrategy(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    @Override
    public String processPayment(double amount) {
        return String.format("Processed PayPal payment of %.2f from account %s", amount,
                accountEmail == null ? "(unknown)" : accountEmail);
    }
}
