package com.example.autofuelx.strategy;

/**
 * Strategy interface for payment processing.
 */
public interface PaymentStrategy {
    /**
     * Process a payment of the given amount and return a human-readable result.
     */
    String processPayment(double amount);
}
