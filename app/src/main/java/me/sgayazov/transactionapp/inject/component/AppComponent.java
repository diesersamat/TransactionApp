package me.sgayazov.transactionapp.inject.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import me.sgayazov.transactionapp.TransactionApp;
import me.sgayazov.transactionapp.data.Interactor;
import me.sgayazov.transactionapp.inject.module.AppModule;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(TransactionApp in);

    void inject(Interactor in);

    Context getContext();

    Interactor getInteractor();
}
