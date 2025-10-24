package com.example.autofuelx.strategy;

public class CardPaymentStrategy implements PaymentStrategy {

    private final String cardNumber;

    public CardPaymentStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String processPayment(double amount) {
        String masked = cardNumber == null ? "****"
                : "****-****-****-"
                        + (cardNumber.length() > 4 ? cardNumber.substring(cardNumber.length() - 4) : cardNumber);
        return String.format("Processed card payment of %.2f using card %s", amount, masked);
    }
}
