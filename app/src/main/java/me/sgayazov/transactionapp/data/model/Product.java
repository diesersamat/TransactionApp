package me.sgayazov.transactionapp.data.model;

public class Product {
    private String sku;
    private int transactionCount;

    public Product(String sku, int transactionCount) {
        this.sku = sku;
        this.transactionCount = transactionCount;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;

        return sku != null ? sku.equals(product.sku) : product.sku == null;

    }

    @Override
    public int hashCode() {
        return sku != null ? sku.hashCode() : 0;
    }
}
