package com.example.tkmybatisdemo.form;

import lombok.Data;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/25 13:51
 * @Description:
 */
@Data
public class ProductListForm{
    private Integer categoryID;
    private String categoryName;
    private String description;

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
