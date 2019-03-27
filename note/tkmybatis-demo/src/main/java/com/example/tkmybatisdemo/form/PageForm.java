package com.example.tkmybatisdemo.form;

import lombok.Data;

import java.util.List;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/25 14:25
 * @Description:
 */
@Data
public class PageForm<T> {

    // 当前页
    private Integer currentPage = 1;

    // 每页显示的总条数
    private Integer pageSize = 10;

    // 总条数
    private Integer totalNum;

    // 总页数
    private Integer totalPage;

    // 是否有下一页
    private Integer isMore;

    // 开始索引
    private Integer startIndex;

    // 分页结果
    private List<T> items;


}
