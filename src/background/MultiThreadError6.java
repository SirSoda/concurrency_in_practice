package background;

import java.util.HashMap;
import java.util.Map;

/**
 * 在构造函数中使用多线程
 * mxf
 * 2019年09月03日20:50:32
 */
public class MultiThreadError6 {

    private Map<String, Object> map;

    public MultiThreadError6() {
        map = new HashMap<>();
        new Thread() {
            @Override
            public void run() {
                map.put("1", "1");
                map.put("2", "2");
            }
        }.start();
    }

    public Map getMap() {
        return map;
    }

    public static void main(String[] args) throws InterruptedException {
        MultiThreadError6 multiThreadError6 = new MultiThreadError6();
//        Thread.sleep(100);
        Map<String, Object> map = multiThreadError6.getMap();
        System.out.println(map.get("1"));
    }
}
