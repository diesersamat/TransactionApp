package me.sgayazov.transactionapp.view.activity;

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
import me.sgayazov.transactionapp.data.model.Transaction;
import me.sgayazov.transactionapp.inject.component.DaggerTransactionsComponent;
import me.sgayazov.transactionapp.presentation.BasePresenter;
import me.sgayazov.transactionapp.presentation.TransactionsPresenter;
import me.sgayazov.transactionapp.view.adapter.TransactionAdapter;
import me.sgayazov.transactionapp.view.interfaces.TransactionsView;

public class TransactionsActivity extends BaseActivity<Transaction> implements TransactionsView {


    @Inject
    TransactionsPresenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.totalText)
    TextView totalText;
    @BindView(R.id.progressBar)
    View progressBar;
    @BindView(R.id.errorText)
    TextView errorText;

    private TransactionAdapter adapter;


    @Override
    public void onDataLoadFailed(String reason) {
        errorText.setVisibility(View.VISIBLE);
        errorText.setText(reason);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        totalText.setVisibility(View.GONE);
    }

    @Override
    public void onDataLoaded(List<Transaction> products) {
        errorText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        adapter.setData(products);
        totalText.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTotalCount(float sumInGBP) {
        totalText.setText(String.format("Total: %sGBP", sumInGBP));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        ButterKnife.bind(this);
        setTitle(getString(R.string.products));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransactionAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected BasePresenter<Transaction> getPresenter() {
        return presenter;
    }

    @Override
    protected void additionalCreateOperations() {
        DaggerTransactionsComponent.builder()
                .appComponent(TransactionApp.getComponent())
                .view(this)
                .sku(getIntent().getStringExtra(SKU))
                .build()
                .inject(this);
    }
}
