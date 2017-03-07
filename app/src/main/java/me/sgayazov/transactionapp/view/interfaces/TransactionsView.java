package me.sgayazov.transactionapp.view.interfaces;

import me.sgayazov.transactionapp.data.model.Transaction;

public interface TransactionsView extends BaseView<Transaction> {
    void onTotalCount(float sumInGBP);
}
