package com.android.architecture.data.entity;

/**
 * Room Entity for Product table.
 * COMPLIANT: Has @Entity annotation and @PrimaryKey field.
 */
@Entity
public class ProductEntity {
    @PrimaryKey
    private int productId;
    private String productName;
    private double price;

    public ProductEntity() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
