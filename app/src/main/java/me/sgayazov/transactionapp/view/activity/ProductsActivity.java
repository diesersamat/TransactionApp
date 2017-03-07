package me.sgayazov.transactionapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sgayazov.transactionapp.R;
import me.sgayazov.transactionapp.TransactionApp;
import me.sgayazov.transactionapp.data.model.Product;
import me.sgayazov.transactionapp.inject.component.DaggerProductsComponent;
import me.sgayazov.transactionapp.presentation.ProductsPresenter;
import me.sgayazov.transactionapp.view.adapter.ProductsAdapter;
import me.sgayazov.transactionapp.view.interfaces.ProductsView;

public class ProductsActivity extends BaseActivity<Product> implements ProductsView {

    @Inject
    ProductsPresenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    View progressBar;
    @BindView(R.id.errorText)
    TextView errorText;

    private ProductsAdapter adapter;

    @Override
    public void onDataLoaded(List<Product> products) {
        errorText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        adapter.setData(products);
    }

    @Override
    public void onDataLoadFailed(String reason) {
        errorText.setVisibility(View.VISIBLE);
        errorText.setText(reason);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public ProductsPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        setTitle(getString(R.string.products));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductsAdapter();
        adapter.setClickListener(new ProductsAdapter.ProductClickListener() {
            @Override
            public void onProductClick(String sku) {
                openSkuTransactions(sku);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void additionalCreateOperations() {
        DaggerProductsComponent.builder()
                .appComponent(TransactionApp.getComponent())
                .view(this)
                .build()
                .inject(this);
    }

    private void openSkuTransactions(String sku) {
        Intent intent = new Intent(this, TransactionsActivity.class);
        intent.putExtra(SKU, sku);
        this.startActivity(intent);
    }
}
