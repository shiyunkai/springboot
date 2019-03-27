package com.example.tkmybatisdemo.service;

import com.example.tkmybatisdemo.domain.Product;

import java.util.List;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/25 14:30
 * @Description:
 */
public interface ProductService {

    List<Product> findProductByPage(Integer currentPage, Integer pageSize);
}
