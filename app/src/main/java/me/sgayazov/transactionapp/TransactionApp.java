package me.sgayazov.transactionapp;

import android.app.Application;

import me.sgayazov.transactionapp.inject.component.AppComponent;
import me.sgayazov.transactionapp.inject.component.DaggerAppComponent;
import me.sgayazov.transactionapp.inject.module.AppModule;

public class TransactionApp extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = initComponent();
    }

    protected AppComponent initComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
