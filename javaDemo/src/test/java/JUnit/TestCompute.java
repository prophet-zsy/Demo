package JUnit;

import org.junit.*;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


/**
 * 每个被Test注解修饰的方法，都会生成一个TestCompute实例运行对应的测试方法
 */

@RunWith(Parameterized.class)   // for参数化测试
public class TestCompute {
    private int param1;   // for参数化测试
    private int param2;   // for参数化测试
    private int res;      // for参数化测试

    private static Object source;
    private Compute ins;

    /**
     * 测试方法之前的工作和之后的工作
     */
    @BeforeClass
    public static void beforeClass() {  // 用于初始化静态资源  // 每次运行只调用一次
        System.out.println("beforeClass");
        source = new Object();
    }

    @AfterClass
    public static void afterClass() {  // 用于释放静态资源  // 每次运行只调用一次
        System.out.println("afterClass");
        source = null;
    }

    @Before
    public void setup() {  // 用于初始化实例  // 每个方法调用一次
        System.out.println("setup");
        ins = new Compute();
    }

    @Before
    public void setup2() {  // 用于初始化实例  // 每个方法调用一次  // 主要依靠注解辨识   // 可定义多个
        System.out.println("setup2");
    }

    @After
    public void teardown() {  // 用于释放实例  // 每个方法调用一次
        System.out.println("teardown");
        ins = null;
    }

    /**
     * 测试方法，使用断言
     */
    @Test
    public void testComputeSum() {  // Test注解修饰的方法，应为无参方法，且返回值为void
        System.out.println("testComputeSum");
        org.junit.Assert.assertEquals(55, ins.computeSum(1, 10));
    }

    @Test
    public void testComputeMultiply() {
        System.out.println("testComputeMultiply");
        org.junit.Assert.assertEquals(3628800, ins.computeMultiply(1, 10));
    }

    /**
     * 测试异常
     */
    @Test()
    public void testComputeSumWithStartMoreEnd0() {  // 使用try catch看是否能捕获到RuntimeException异常       //测试异常写法1
        System.out.println("testComputeSumWithStartMoreEnd0");
//        org.junit.Assert.assertThrows(RuntimeException.class, new ThrowingRunnable() {  // 也可以这样写   //测试异常写法2
//            @Override
//            public void run() throws Throwable {
//                ins.computeSum(10, 1);
//            }
//        });
        try {
            ins.computeSum(10, 1);
            org.junit.Assert.fail("you should throw RuntimeException");
        } catch (RuntimeException e) {
            org.junit.Assert.assertEquals(RuntimeException.class, e.getClass());
        }
    }

    @Test(expected = RuntimeException.class)  // 断言该方法抛出RuntimeException异常                            //测试异常写法3
    public void testComputeSumWithStartMoreEnd() {
        System.out.println("testComputeSumWithStartMoreEnd");
        ins.computeSum(10, 1);
    }

    @Test(expected = RuntimeException.class)
    public void testComputeMultiplyWithStartMoreEnd() {
        System.out.println("testComputeMultiplyWithStartMoreEnd");
        ins.computeSum(10, 1);
    }

    /**
     * 参数化测试
     * for 用例
     *      for 方法
     * 断言
     * 网格：要考虑每条用例对每个测试方法的组合
     */
    @Parameterized.Parameters  // 提供数据（包括参数和结果）  // todo 该方法会先于@BeforeClass方法执行，而且针对每条用例数据都会经过当前类中所有@Test测试，相当于两层for循环
    public static Collection<?> data() {
        System.out.println("data 准备数据");
        return Arrays.asList(new Object[][]{
                {5, 6, 11}, {7, 9, 24}, {1, 1, 1}, {12, 23, 210}
        });
    }

    public TestCompute(int param1, int param2, int res) {  // 利用构造函数初始化生成每条测试用例的实例
        System.out.println("TestCompute");
        this.param1 = param1;
        this.param2 = param2;
        this.res = res;
    }

    @Test
    public void testComputeSumParameters() {
        System.out.println("testComputeSumParameters");
        org.junit.Assert.assertEquals(this.res, ins.computeSum(this.param1, this.param2));
    }

    /**
     * 超时测试
     */
    @Test(timeout = 40)  // 单位ms  // 在40ms内计算出正确数据
    public void testComputeSumTimeout() {
        System.out.println("testComputeSumTimeout");
        org.junit.Assert.assertEquals(5000000050000000L, ins.computeSum(1, 100000000));
    }
}
