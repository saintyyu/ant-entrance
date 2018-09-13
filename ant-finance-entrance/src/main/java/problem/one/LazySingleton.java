package problem.one;

import java.io.Serializable;

/**
 * Created by cdyujing7 on 2018/9/12.
 * 懒汉式单例模式
 * 说明：最佳单例模式的实现是用枚举的方式，这里实现Serializable是为了防止序列化破坏单例，
 * 而添加flag是为防止反射破坏单例。
 */
public class LazySingleton implements Serializable{

    private static final long serialVersionUID = -5283076466704494588L;

    private volatile static LazySingleton singleton = null;

    private volatile static boolean flag = true;

    private LazySingleton() {
        if(flag){
            flag = false;
        }else{
            throw new RuntimeException("illegal operation!");
        }
    }

    //对外提供的获取单例的方法
    public static LazySingleton getInstance() {
        if (singleton == null) {
            synchronized (LazySingleton.class) {
                if (singleton == null) {
                    singleton = new LazySingleton();
                }
            }
        }
        return singleton;
    }

    private Object readResolve() {
        return singleton;
    }
}
