package com.example.tkmybatisdemo.service.impl;

import com.example.tkmybatisdemo.dao.ProductDao;
import com.example.tkmybatisdemo.domain.Product;
import com.example.tkmybatisdemo.form.PageForm;
import com.example.tkmybatisdemo.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/25 14:31
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public List<Product> findProductByPage(Integer currentPage, Integer pageSize) {
        // 设置分页信息,必须在mapper接口中的方法执行之前设置，此时在products中返回的数据就会进行分页
        PageHelper.startPage(currentPage,pageSize);
        List<Product> products = productDao.selectAll();


        return products;
    }
}
