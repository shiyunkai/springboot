package com.example.tkmybatisdemo.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/25 10:24
 * @Description:
 */
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    private Integer productID;
    private String productName;
    private BigDecimal price;
    @Column(name = "category_id")
    private Integer categoryID;

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }
}