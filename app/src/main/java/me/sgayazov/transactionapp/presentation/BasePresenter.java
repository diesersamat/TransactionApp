package me.sgayazov.transactionapp.presentation;

import me.sgayazov.transactionapp.data.Interactor;
import me.sgayazov.transactionapp.view.interfaces.BaseView;

public abstract class BasePresenter<T> {

    private final Interactor interactor;

    private final BaseView<T> view;

    BasePresenter(BaseView<T> view, Interactor interactor) {
        this.interactor = interactor;
        this.view = view;
    }

    public abstract void onStartLoad();

    protected Interactor getInteractor() {
        return interactor;
    }

    protected BaseView<T> getView() {
        return view;
    }
}
