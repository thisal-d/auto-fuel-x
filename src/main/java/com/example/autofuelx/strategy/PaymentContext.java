package com.example.autofuelx.strategy;


public class PaymentContext {
    private PaymentStrategy paymentStrategy;


    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }


    public String processPayment(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set");
        }
        return paymentStrategy.processPayment(amount);
    }
}