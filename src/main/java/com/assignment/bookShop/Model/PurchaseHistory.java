package com.assignment.bookShop.Model;

import java.util.Date;

public class PurchaseHistory {
    String purchaseId;
    int userId;
    int bookId;
    Date purchaseDate;
    double purchasePrice;

    public PurchaseHistory() {
    }

    public PurchaseHistory(String purchaseId, int userId, int bookId, Date purchaseDate, double purchasePrice) {
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.bookId = bookId;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
