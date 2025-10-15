package com.example.autofuelx.strategy;

public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public String processPayment(double amount) {
        return String.format("Recorded cash payment of %.2f (no external gateway required)", amount);
    }
}
