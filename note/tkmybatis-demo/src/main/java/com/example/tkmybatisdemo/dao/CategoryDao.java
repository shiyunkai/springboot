package com.example.tkmybatisdemo.dao;

import com.example.tkmybatisdemo.domain.Category;
import org.mybatis.spring.annotation.MapperScan;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/25 10:04
 * @Description: tkmybatis测试, 没有xml文件
 */
public interface CategoryDao extends Mapper<Category> {

}
