package me.sgayazov.transactionapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sgayazov.transactionapp.R;
import me.sgayazov.transactionapp.data.model.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductItemViewHolder> {
    ProductClickListener clickListener;
    private List<Product> products;

    public void setData(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public ProductItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductItemViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ProductItemViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    public void setClickListener(ProductClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ProductClickListener {
        void onProductClick(String sku);
    }

    class ProductItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sku)
        TextView sku;
        @BindView(R.id.transNumber)
        TextView transNumber;


        ProductItemViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false));
            ButterKnife.bind(this, itemView);
        }

        void bind(final Product product) {
            sku.setText(product.getSku());
            transNumber.setText(itemView.getContext().getString(R.string.trans_number,
                    product.getTransactionCount()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onProductClick(product.getSku());
                    }
                }
            });
        }
    }
}
