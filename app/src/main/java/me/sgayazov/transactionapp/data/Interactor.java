package me.sgayazov.transactionapp.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import me.sgayazov.transactionapp.TransactionApp;
import me.sgayazov.transactionapp.data.model.Product;
import me.sgayazov.transactionapp.data.model.Rate;
import me.sgayazov.transactionapp.data.model.Transaction;
import me.sgayazov.transactionapp.data.model.TransactionWrapper;
import me.sgayazov.transactionapp.data.provider.RateDataProvider;
import me.sgayazov.transactionapp.data.provider.TransactionDataProvider;

public class Interactor {

    private static final String DEF_CURRENCY = "GBP";
    @Inject
    RateDataProvider rateProvider;

    @Inject
    TransactionDataProvider transactionProvider;

    public Interactor() {
        TransactionApp.getComponent().inject(this);
    }

    public List<Product> getProducts() throws IOException {
        List<Transaction> transactionList = transactionProvider.getTransactionList(false);

        HashMap<String, Integer> result = new LinkedHashMap<>();

        for (Transaction transaction : transactionList) {
            if (result.containsKey(transaction.getSku())) {
                result.put(transaction.getSku(), result.get(transaction.getSku()) + 1);
            } else {
                result.put(transaction.getSku(), 1);
            }
        }

        List<Product> products = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            products.add(new Product(entry.getKey(), entry.getValue()));
        }
        return products;
    }

    public TransactionWrapper getTransactionsBySKU(String sku) throws IOException {
        TransactionWrapper transactionWrapper = new TransactionWrapper();
        List<Transaction> transactionList = transactionProvider.getTransactionList(false, sku);
        List<Rate> rateList = rateProvider.getRateList(false);

        float sumInGBP = 0;

        for (Transaction transaction : transactionList) {
            transaction.setAmountInGBP(convertAmount(transaction.getCurrency(),
                    DEF_CURRENCY, transaction.getAmount(), rateList));
            sumInGBP += transaction.getAmountInGBP();
        }

        transactionWrapper.setTransactionList(transactionList);
        transactionWrapper.setSumInGBP(sumInGBP);

        return transactionWrapper;
    }

    private float convertAmount(String from, String to, float amount, List<Rate> rates) {
        if (isCurrenciesAreEqual(from, to)) {
            return amount;
        }

        List<Rate> ratesConvertingTo = getRatesConvertingTo(to, rates);

        //check if we can convert to our currency at all
        if (ratesConvertingTo.isEmpty()) {
            return Float.MIN_VALUE;
        }


        Rate conversionRate = null;
        for (Rate rate : ratesConvertingTo) {
            if (isCurrenciesAreEqual(rate.getFrom(), from)) {
                conversionRate = rate;
            }
        }
        //if we found correct conversion rate
        if (conversionRate != null) {
            return amount * conversionRate.getRate();
        }

        //if conversion rate not found, we have to try to find it recursively
        float conversionRecursive = Float.MIN_VALUE;
        for (Rate rateConvertingTo : ratesConvertingTo) {
            float rate = convertAmount(from, rateConvertingTo.getFrom(), amount,
                    calculateDifference(rates, ratesConvertingTo));
            if (rate > 0 && (conversionRecursive == Float.MIN_VALUE
                    || conversionRecursive > rate * rateConvertingTo.getRate())) {
                conversionRecursive = rate * rateConvertingTo.getRate();
            }
        }
        return conversionRecursive == Float.MIN_VALUE ? Float.MIN_VALUE : conversionRecursive * amount;
    }

    private boolean isCurrenciesAreEqual(String from, String to) {
        return from.equals(to);
    }

    private List<Rate> getRatesConvertingTo(String to, List<Rate> rates) {
        List<Rate> ratesTo = new LinkedList<>();
        for (Rate rate : rates) {
            if (rate.getTo().equals(to)) {
                ratesTo.add(rate);
            }
        }

        return ratesTo;
    }

    private List<Rate> calculateDifference(List<Rate> rates1, List<Rate> rates2) {
        List<Rate> rates = new ArrayList<>();
        for (Rate rate : rates1) {
            if (!rates2.contains(rate)) {
                rates.add(rate);
            }
        }
        return rates;
    }
}
