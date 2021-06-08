import com.demo.product.domain.Product;
import com.demo.product.handle.ElasticsearchClientHandle;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ElasticsearchTest
 * @Description: TODO
 * @Author zly
 * @Date 2021/6/1
 **/
public class ElasticsearchTest extends BaseJunitTest{

    @Autowired
    private ElasticsearchClientHandle elasticsearchClientHandle;

    @Test
    public void testCreateIndex() throws IOException {
        elasticsearchClientHandle.createIndex("test_index_1");
    }

    @Test
    public void testCreateDocument() throws IOException {
        Product product = new Product();
        product.setId(3);
        product.setName("华为手机");
        product.setStore(10);
        product.setPrice(Long.parseLong("5000"));
        product.setCreateTime(new Date());
        String documentJson = new ObjectMapper().writeValueAsString(product);
        elasticsearchClientHandle.createDocument("test_index_1", documentJson);
    }

    @Test
    public void testGetDocument() throws IOException {
        String document = elasticsearchClientHandle.getDocument("test_index_1", "fe8fc8ec-ddda-4532-88ce-a032f3244e4f");
        System.out.println(document);
    }

    @Test
    public void testBulkCreateDocument() throws IOException {
        List<String> documentJsonList = new ArrayList<>();
        String[] productNames = new String[]{"苹果手机","华为手机","OPPO手机","VIVO手机","小米手机","荣耀手机","小米手环","小米电视","小米路由器","华为手环","华为智慧屏","java编程","C++编程","PHP编程","python编程","Ruby编程","mysql数据库","Oracle数据库","MongoDB数据库","DB2数据库","Java入门","java进阶"};
        for (int i = 1; i <= productNames.length; i++) {
            Product product = new Product();
            product.setId(i);
            product.setName(productNames[i-1]);
            product.setStore(i);
            product.setPrice((long) (i*10));
            product.setCreateTime(localDateTimeToDate(LocalDateTime.now().minusDays(i)));
            String documentJson = new ObjectMapper().writeValueAsString(product);
            documentJsonList.add(documentJson);
        }
        elasticsearchClientHandle.bulkCreateDocument("test_index_1", documentJsonList);
    }

    @Test
    public void testSearchDocument() throws IOException {
        List<String> list = elasticsearchClientHandle.searchDocument("test_index_1", new String[]{"苹果"}, localDateTimeToDate(LocalDateTime.now().minusDays(100)), localDateTimeToDate(LocalDateTime.now()));
        System.out.println(list);
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

}
