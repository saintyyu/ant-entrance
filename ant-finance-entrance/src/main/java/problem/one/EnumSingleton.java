package problem.one;

/**
 * Created by cdyujing7 on 2018/9/12.
 * 枚举方式实现单例模式
 */
public class EnumSingleton {
    private EnumSingleton() {
    }

    //对外提供的获取单例的方法
    public static EnumSingleton getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private EnumSingleton singleton;

        Singleton() {
            singleton = new EnumSingleton();
        }

        public EnumSingleton getInstance() {
            return singleton;
        }
    }
}
