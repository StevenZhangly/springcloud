import com.demo.product.ProductServerApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * @ClassName BaseJunitTest
 * @Description: TODO
 * @Author zly
 * @Date 2021/4/8
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServerApplication.class)
public class BaseJunitTest {

    @Autowired
    protected WebApplicationContext context;
    protected MockMvc mockMvc;

}
