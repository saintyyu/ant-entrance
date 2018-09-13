package problem.three;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cdyujing7 on 2018/9/13.
 */
public class MainThreadTest {
    @Test
    public void mainTest() {
        try {
            MainThread.main(null);
        } catch (InterruptedException e) {
            Assert.assertTrue(false);
        }
    }
}
