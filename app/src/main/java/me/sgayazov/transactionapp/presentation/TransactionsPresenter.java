package me.sgayazov.transactionapp.presentation;

import java.io.IOException;

import javax.inject.Inject;

import me.sgayazov.transactionapp.data.Interactor;
import me.sgayazov.transactionapp.data.model.Transaction;
import me.sgayazov.transactionapp.data.model.TransactionWrapper;
import me.sgayazov.transactionapp.view.interfaces.TransactionsView;

public class TransactionsPresenter extends BasePresenter<Transaction> {

    private final String sku;

    @Inject
    TransactionsPresenter(TransactionsView view, Interactor interactor, String sku) {
        super(view, interactor);
        this.sku = sku;
    }

    @Override
    public void onStartLoad() {
        try {
            TransactionWrapper wrapper = getInteractor().getTransactionsBySKU(sku);
            if (wrapper == null) {
                getView().onDataLoadFailed("Error!");
                return;
            }
            if (wrapper.getTransactionList().isEmpty()) {
                getView().onDataLoadFailed("Empty list!");
                return;
            }
            getView().onTotalCount(wrapper.getSumInGBP());
            getView().onDataLoaded(wrapper.getTransactionList());
        } catch (IOException e) {
            e.printStackTrace();
            getView().onDataLoadFailed(e.getLocalizedMessage());
        }
    }

    @Override
    protected TransactionsView getView() {
        return (TransactionsView) super.getView();
    }
}
