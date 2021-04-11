import com.yc.biz.Calaulator;
import com.yc.junit.*;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-03-31 19:43
 */
public class MyCalculatorTest {
    private Calaulator cal;//待测试的单元

    @MyBeforeClass
    public static void bc() {
        System.out.println("beforeClass");
    }

    @MyAfterClass
    public static void ac() {
        System.out.println("AfterClass");
    }

    @MyBefore//执行测试方法前要调用
    public void setUp() throws Exception {
        System.out.println("before");
        cal = new Calaulator();
    }

    @MyAfter//执行测试方法后要调用
    public void tearDown() throws Exception {
        System.out.println("AFTER");
    }

    @MyTest
    public void testAdd() {
        System.out.println("add测试");
    }

    @MyTest
    public void testSub() {
        System.out.println("sub测试");
    }
}
