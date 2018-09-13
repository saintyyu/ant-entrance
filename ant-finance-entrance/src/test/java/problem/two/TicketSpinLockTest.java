package problem.two;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cdyujing7 on 2018/9/12.
 */
public class TicketSpinLockTest implements Runnable{

    TicketSpinLock spinLock = new TicketSpinLock();

    private volatile static int index = 0;
    private int threadNum = 1000;//线程数

    @Test
    public void ticketSpinLockTest() {
        for (int i=0;i<threadNum;i++) {
            new Thread(new TicketSpinLockTest()).start();
        }

        ThreadGroup group = Thread.currentThread().getThreadGroup();
        int num = group.activeCount() - 2;//这里之所以减2，是因为线程组里还有两个线程：main和Monitor Ctrl-Break线程
        while (num > 0) {
            num = group.activeCount() - 2;//这里之所以减2，是因为线程组里还有两个线程：main和Monitor Ctrl-Break线程
            try {
                Thread.sleep(1000);//睡眠1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(index == threadNum);

    }

    @Override
    public void run() {
        int ticket = spinLock.lock();
        index++;
        spinLock.unlock(ticket);
    }
}
