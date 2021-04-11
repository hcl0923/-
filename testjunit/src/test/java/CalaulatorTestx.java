import org.junit.*;

//测试用例  类
public class CalaulatorTestx {
    private Calaulator cal;//待测试的单元

    @BeforeClass
    public static void bc() {
        System.out.println("beforeClass");
    }

    @AfterClass
    public static void ac() {
        System.out.println("AfterClass");
    }

    @Before   //执行测试方法前要调用的
    public void setUp() throws Exception {
        System.out.println("before");
        cal = new Calaulator();
    }

    @After    //执行测试方法后要调用的
    public void tearDown() throws Exception {
        System.out.println("AFTER");
    }

    @Test
    public void testAdd() {
        System.out.println("add测试");
        Assert.assertEquals(3, cal.add(1, 2));
    }

    @Test
    public void testSub() {
        System.out.println("sub测试");
        Assert.assertEquals(1, cal.sub(2, 1));
    }
}