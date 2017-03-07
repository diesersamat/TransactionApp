package me.sgayazov.transactionapp.inject.component;

import dagger.BindsInstance;
import dagger.Component;
import me.sgayazov.transactionapp.inject.PerFragment;
import me.sgayazov.transactionapp.view.activity.TransactionsActivity;
import me.sgayazov.transactionapp.view.interfaces.TransactionsView;

@Component(dependencies = AppComponent.class)
@PerFragment
public interface TransactionsComponent {
    void inject(TransactionsActivity in);

    @Component.Builder
    interface Builder {
        Builder appComponent(AppComponent appComponent);

        @BindsInstance
        Builder view(TransactionsView view);

        @BindsInstance
        Builder sku(String sku);

        TransactionsComponent build();
    }
}
