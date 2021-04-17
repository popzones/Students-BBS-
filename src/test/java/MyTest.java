import com.saybiu.service.UserService;
import com.saybiu.utils.JwtUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test()
    {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("conf/ApplicationContext.xml");
        UserService userService= (UserService) applicationContext.getBean("userServiceImpl");
        userService.userSupportAndUserBeSupported(1,14);
    }
}
