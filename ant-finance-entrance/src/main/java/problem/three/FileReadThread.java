package problem.three;

/**
 * Created by cdyujing7 on 2018/9/12.
 *
 * 文件读取线程：
 */
public class FileReadThread implements Runnable {
    @Override
    public void run() {
        while (MainThread.filesToBeRead.size() > 0) {
            String filePath = MainThread.filesToBeRead.poll();//这里不能用阻塞方法，可能导致死锁
            if (filePath == null) {//所有文件已经被读取完
                return;
            }
            MainThread.readFileAndPutIntoQueue(filePath);//读取文件并存储到链表中
        }
        MainThread.aliveFileReadingThreadNum.getAndDecrement();//当前线程退出前重置存活的文件读取线程数
    }
}
