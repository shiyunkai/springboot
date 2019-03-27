package com.example.tkmybatisdemo.utils;

import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/25 14:06
 * @Description:
 */
@Data
public class PageUtils<T> implements Serializable {

    private List<T> list;

    private Integer pageNum;

    private Integer pageSize;

    private long total;

    public PageUtils(){

    }

    public PageUtils(List<T> list){
        if(list instanceof Page){
            Page page = (Page) list;
            this.total = page.getTotal();
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.list = list;
        }
    }

    public PageUtils(Integer total, List<T> list, Integer pageNum, Integer pageSize){
        this.list = list;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }
}
