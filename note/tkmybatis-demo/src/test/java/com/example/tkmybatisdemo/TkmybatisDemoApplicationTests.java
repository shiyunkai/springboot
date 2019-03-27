package com.example.tkmybatisdemo;

import com.example.tkmybatisdemo.dao.CategoryDao;
import com.example.tkmybatisdemo.dao.ProductDao;
import com.example.tkmybatisdemo.domain.Category;
import com.example.tkmybatisdemo.domain.Product;
import com.example.tkmybatisdemo.form.ProductListForm;
import com.example.tkmybatisdemo.service.ProductService;
import com.example.tkmybatisdemo.utils.PageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TkmybatisDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    @Test
    public void selectAllTest(){
        List<Category> categories = categoryDao.selectAll();
        for (Category c : categories){
            System.out.println("name:"+c.getCategoryName());
        }

        List<Product> products = productDao.selectAll();
        for (Product product: products){
            System.out.println("product name:"+product.getProductName());
        }
    }

    /* * @Auther: shiyunkai
     * @Description: //使用tkmybatis进行条件查询
     * @Param: []
     * @Date:  10:43 2019/3/25
     * @return: void
     **/
    @Test
    public void testCriteria(){
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryID",1);
        criteria.orEqualTo("categoryName","Oil");
        List<Category> categories = categoryDao.selectByExample(example);
        for (Category c:categories){
            System.out.println("category name "+c.getCategoryName());
        }
    }

    @Autowired
    ProductService productService;

    /* * @Auther: shiyunkai
     * @Description: 测试mybatis的分页插件的使用
     * @Param: []
     * @Date:  13:53 2019/3/25
     * @return: void
     **/
    @Test
    public void testPage(){
        ProductListForm pf = new ProductListForm();
        pf.setPageNum(5);
        pf.setPageSize(3);
        List<Product> productByPage = productService.findProductByPage(pf.getPageNum(), pf.getPageSize());

    }



}
