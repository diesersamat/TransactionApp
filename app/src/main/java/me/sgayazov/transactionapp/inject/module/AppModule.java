package me.sgayazov.transactionapp.inject.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.sgayazov.transactionapp.data.Interactor;
import me.sgayazov.transactionapp.data.provider.RateDataProvider;
import me.sgayazov.transactionapp.data.provider.TransactionDataProvider;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Interactor provideInteractor() {
        return new Interactor();
    }

    @Provides
    @Singleton
    RateDataProvider provideRateDataProvider(Context context, Gson gson) {
        return new RateDataProvider(context, gson);
    }

    @Provides
    @Singleton
    TransactionDataProvider provideTransactionDataProvider(Context context, Gson gson) {
        return new TransactionDataProvider(context, gson);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .create();
    }
}
