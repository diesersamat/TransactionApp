package me.sgayazov.transactionapp.data.model;

public class Rate {
    private String from;
    private float rate;
    private String to;

    public Rate(String from, float rate, String to) {
        this.from = from;
        this.rate = rate;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
