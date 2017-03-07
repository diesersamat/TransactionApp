package me.sgayazov.transactionapp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.sgayazov.transactionapp.presentation.BasePresenter;
import me.sgayazov.transactionapp.view.interfaces.BaseView;

public abstract class BaseActivity<T> extends AppCompatActivity implements BaseView<T> {

    public static final String SKU = "SKU";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        additionalCreateOperations();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onStartLoad();
    }

    protected abstract BasePresenter<T> getPresenter();

    protected abstract void additionalCreateOperations();
}
