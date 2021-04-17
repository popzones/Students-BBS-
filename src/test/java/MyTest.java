import com.saybiu.utils.JwtUtil;
import org.junit.Test;

public class MyTest {
    @Test
    public void test()
    {
        String token ="Bearer eyJhbGciOiJIUzI1NiIsI.eyJpc3MiOiJodHRwczotcGxlL.mFrs3Zo8eaSNcxiNfvRh9dqKP4F1cB";
        System.out.println(JwtUtil.getUserId(token));
    }
}
