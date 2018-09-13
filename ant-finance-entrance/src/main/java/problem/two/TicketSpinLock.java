package problem.two;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cdyujing7 on 2018/9/12.
 * 基于TicketSpinLock的自旋锁
 */
public class TicketSpinLock {
    //当前服务号
    private static AtomicInteger serviceNum = new AtomicInteger(0);
    //线程排队号
    private static AtomicInteger ticketNum = new AtomicInteger(0);

    private static final ThreadLocal<Integer> myTicketLocal = new ThreadLocal<Integer>();

    //加锁
    public static int lock() {
        int myTicket = ticketNum.getAndIncrement();
        while (myTicket != serviceNum.get()) {
            //spin
        }
        return myTicket;
    }

    //解锁
    public static void unlock(int myTicket) {
        int next = myTicket + 1;
        serviceNum.compareAndSet(myTicket, next);
    }


}
