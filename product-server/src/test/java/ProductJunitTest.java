import com.demo.product.domain.Product;
import com.demo.product.mapper.ProductMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ProductJunitTest
 * @Description: TODO
 * @Author zly
 * @Date 2021/4/8
 **/
public class ProductJunitTest extends BaseJunitTest{

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void queryListTest(){
        List<Product> list = productMapper.queryList();
        System.out.println(list);
    }

    @Test
    public void addProductTest(){
        Product p = new Product();
        p.setName("面包");
        p.setPrice((long)3);
        p.setStore(10);
        p.setCreateTime(new Date());
        p.setUpdateTime(new Date());
        productMapper.insertSelective(p);
    }
}
