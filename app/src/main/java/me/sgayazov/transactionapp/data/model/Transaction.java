package me.sgayazov.transactionapp.data.model;

public class Transaction {
    private float amount;
    private float amountInGBP;
    private String sku;
    private String currency;

    public Transaction(float amount, String sku, String currency) {
        this(amount, 0, sku, currency);
    }

    public Transaction(float amount, float amountInGBP, String sku, String currency) {
        this.amount = amount;
        this.sku = sku;
        this.currency = currency;
        this.amountInGBP = amountInGBP;
    }

    public float getAmountInGBP() {
        return amountInGBP;
    }

    public void setAmountInGBP(float amountInGBP) {
        this.amountInGBP = amountInGBP;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
