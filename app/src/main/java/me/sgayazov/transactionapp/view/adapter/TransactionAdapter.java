package me.sgayazov.transactionapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sgayazov.transactionapp.R;
import me.sgayazov.transactionapp.data.model.Transaction;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionItemViewHolder> {
    private List<Transaction> transactionsList;

    public void setData(List<Transaction> products) {
        this.transactionsList = products;
        notifyDataSetChanged();
    }

    @Override
    public TransactionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TransactionItemViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(TransactionItemViewHolder holder, int position) {
        holder.bind(transactionsList.get(position));
    }

    @Override
    public int getItemCount() {
        return transactionsList == null ? 0 : transactionsList.size();
    }

    class TransactionItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.their_currency)
        TextView currencyAmount;
        @BindView(R.id.gbp_currency)
        TextView gbpAmount;


        TransactionItemViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false));
            ButterKnife.bind(this, itemView);
        }

        void bind(final Transaction transaction) {
            currencyAmount.setText(String.format("%s%s", transaction.getCurrency(), transaction.getAmount()));
            if (transaction.getAmountInGBP() == Float.MIN_VALUE) {
                gbpAmount.setText("No rate");
            }
            gbpAmount.setText(String.format("GBP%s", transaction.getAmountInGBP()));
        }
    }
}
