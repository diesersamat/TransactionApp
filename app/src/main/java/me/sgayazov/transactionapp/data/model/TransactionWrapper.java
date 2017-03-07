package me.sgayazov.transactionapp.data.model;

import java.util.List;

public class TransactionWrapper {
    private List<Transaction> transactionList;
    private float sumInGBP;

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public float getSumInGBP() {
        return sumInGBP;
    }

    public void setSumInGBP(float sumInGBP) {
        this.sumInGBP = sumInGBP;
    }
}
