package me.sgayazov.transactionapp.presentation;

import java.io.IOException;

import javax.inject.Inject;

import me.sgayazov.transactionapp.data.Interactor;
import me.sgayazov.transactionapp.data.model.Product;
import me.sgayazov.transactionapp.view.interfaces.ProductsView;

public class ProductsPresenter extends BasePresenter<Product> {

    @Inject
    ProductsPresenter(ProductsView view, Interactor interactor) {
        super(view, interactor);
    }

    @Override
    public void onStartLoad() {
        try {
            getView().onDataLoaded(getInteractor().getProducts());
        } catch (IOException e) {
            e.printStackTrace();
            getView().onDataLoadFailed(e.getLocalizedMessage());
        }
    }
}
