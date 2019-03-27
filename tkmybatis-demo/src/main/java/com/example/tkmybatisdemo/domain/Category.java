package com.example.tkmybatisdemo.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/25 10:23
 * @Description:
 */
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "category_id")
    private Integer categoryID;
    private String categoryName;
    private String description;


    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
