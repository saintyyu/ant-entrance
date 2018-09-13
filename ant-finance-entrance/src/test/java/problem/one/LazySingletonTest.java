package problem.one;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cdyujing7 on 2018/9/12.
 */
public class LazySingletonTest {

    private static LazySingleton singleton1 = null;
    private static LazySingleton singleton2 = null;

    /**
     * 更充分的单元测试还应包括通过反射机制以及序列化和反序列化，这里只做了简单的并发测试
     */
    @Test
    public void getInstanceTest() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                singleton1 = LazySingleton.getInstance();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                singleton2 = LazySingleton.getInstance();
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(singleton1 != null && singleton1 == singleton2);
    }

}
