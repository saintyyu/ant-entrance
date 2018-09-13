package problem.three;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cdyujing7 on 2018/9/12.
 * 调度主线程：负责读取文件夹下文件信息，并启动文件读取线程和排序线程
 */
public class MainThread {

    //读取文件的线程池数量
    private static final int THREAD_POOL_NUM = 10;
    //文件夹路径，测试文件夹在resources下
    private static final String DICTORY_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\test";

    //当前存活的读取文件的线程数量
    public static AtomicInteger aliveFileReadingThreadNum = new AtomicInteger(THREAD_POOL_NUM);

    //存储文件夹下还未被读取的文件
    public static LinkedBlockingQueue<String> filesToBeRead = new LinkedBlockingQueue<String>();

    //存储文件读取线程读取到的数据
    public static LinkedBlockingQueue<String> dataToBeSorted = new LinkedBlockingQueue<String>();


    public static void main(String[] args) throws InterruptedException {

        readFiles(DICTORY_PATH);//读取文件夹下的文件信息
        if (filesToBeRead.size() == 0) {//文件夹下文件为空则直接返回
            return;
        }

        for (int i=0; i<THREAD_POOL_NUM; i++) {//开启文件读取线程
            new Thread(new FileReadThread()).start();
        }

        Thread searchThread = new Thread(new SearchThread());
        searchThread.start();//开启排序线程

        searchThread.join();//文件读取线程一定会先于排序线程结束，这里主线程需要等待排序线程执行完毕
    }

    //读取指定文件夹下的文件列表
    public static void readFiles(String path) {
        File file = new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                try {
                    filesToBeRead.put(tempList[i].toString());//阻塞方法插入元素，防止插入失败
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //按行读取文件并存储到链表中
    public static void readFileAndPutIntoQueue(String path) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                dataToBeSorted.put(line);//阻塞方法插入元素，防止插入失败
            }
        } catch (Exception e) {
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
            }
        }
    }

}
