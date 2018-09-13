package problem.three;

import problem.three.dto.Datum;

import java.util.*;

/**
 * Created by cdyujing7 on 2018/9/12.
 * 排序查找线程
 */
public class SearchThread implements Runnable {


    @Override
    public void run() {
        HashMap<String, Datum> hashMap = new HashMap<String, Datum>();
        String string;
        //还有datum没处理完
        while (MainThread.aliveFileReadingThreadNum.get() != 0 || MainThread.dataToBeSorted.size() > 0) {
            string = MainThread.dataToBeSorted.poll();
            if (string != null && !string.trim().equals("")) {
                String[] strings = string.split(",");
                Datum newDatum = new Datum();
                newDatum.setId(strings[0]);
                newDatum.setGroupId(strings[1]);
                newDatum.setQuota(Float.parseFloat(strings[2]));
                Datum oldDatum = hashMap.get(newDatum.getGroupId());
                if (oldDatum == null || newDatum.getQuota().compareTo(oldDatum.getQuota()) < 0) {
                    hashMap.put(newDatum.getGroupId(), newDatum);
                }
            }
        }
        Set<String> keySet = hashMap.keySet();
        if (keySet.size() > 0) {
            ArrayList<String> list = new ArrayList<String>(keySet);
            Collections.sort(list, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            for (String key : list) {
                Datum datum = hashMap.get(key);
                System.out.println(datum.getGroupId() + "," + datum.getId() + "," + datum.getQuota());
            }
        }
    }
}
