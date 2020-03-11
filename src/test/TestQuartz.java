import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_quartz.xml")
public class TestQuartz {

    @Test
    public void test1() throws InterruptedException {
        System.out.println("MyJobText");
        Thread.sleep(10000);
    }
}
