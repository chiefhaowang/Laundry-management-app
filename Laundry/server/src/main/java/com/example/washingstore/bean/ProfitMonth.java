package com.example.washingstore.bean;

public class ProfitMonth {
    private int date;
    private int profit;

    public ProfitMonth(int i, int total) {
        this.date=i;
        this.profit=total;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}
