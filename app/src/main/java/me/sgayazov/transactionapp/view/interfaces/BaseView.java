package me.sgayazov.transactionapp.view.interfaces;

import java.util.List;

public interface BaseView<T> {

    void onDataLoadFailed(String reason);

    void onDataLoaded(List<T> products);

}
