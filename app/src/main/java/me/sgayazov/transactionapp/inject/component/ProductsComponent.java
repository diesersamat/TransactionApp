package me.sgayazov.transactionapp.inject.component;

import dagger.BindsInstance;
import dagger.Component;
import me.sgayazov.transactionapp.inject.PerFragment;
import me.sgayazov.transactionapp.view.activity.ProductsActivity;
import me.sgayazov.transactionapp.view.interfaces.ProductsView;

@Component(dependencies = AppComponent.class)
@PerFragment
public interface ProductsComponent {
    void inject(ProductsActivity in);

    @Component.Builder
    interface Builder {
        Builder appComponent(AppComponent appComponent);

        @BindsInstance
        Builder view(ProductsView view);

        ProductsComponent build();
    }
}
