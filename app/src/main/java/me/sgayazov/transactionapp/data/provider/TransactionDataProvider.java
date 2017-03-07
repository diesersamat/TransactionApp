package me.sgayazov.transactionapp.data.provider;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import me.sgayazov.transactionapp.R;
import me.sgayazov.transactionapp.data.model.Transaction;

public class TransactionDataProvider extends BaseDataProvider {

    @Inject
    public TransactionDataProvider(Context context, Gson gson) {
        super(gson, context);
    }

    public List<Transaction> getTransactionList(boolean isUseSecond) throws IOException {
        return gson.fromJson(readFile(isUseSecond ? R.raw.second_transactions : R.raw.first_transactions),
                new TypeToken<List<Transaction>>() {
                }.getType());
    }

    public List<Transaction> getTransactionList(boolean isUseSecond, String sku) throws IOException {
        List<Transaction> transactions = gson.fromJson(readFile(isUseSecond ? R.raw.second_transactions : R.raw.first_transactions),
                new TypeToken<List<Transaction>>() {
                }.getType());

        Iterator<Transaction> it = transactions.iterator();
        while (it.hasNext()) {
            if (it.next().getSku().equals(sku)) {
                it.remove();
            }
        }
        return transactions;
    }
}
