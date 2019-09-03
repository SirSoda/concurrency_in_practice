package background;

import jdk.nashorn.internal.codegen.ObjectCreator;

import java.util.HashMap;
import java.util.Map;

/**
 * 多线程常见错误 发布对象溢出 私有属性发布出去造成泄漏
 * mxf
 * 2019年09月01日10:50:59
 */
public class MultiThreadError3 {

    private Map<String, Object> map;

    public MultiThreadError3() {
        map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
    }

    public Map getMap() {
        return map;
    }

    /**
     * 用副本的方法解决溢出
     * mxf
     * 2019年09月03日21:00:00
     * @return
     */
    public Map getMapImproved() {
        return new HashMap(map);
    }

    public static void main(String[] args) {
        MultiThreadError3 multiThreadError3 = new MultiThreadError3();
//        Map<String, Object> map = multiThreadError3.getMap();
        Map<String, Object> map = multiThreadError3.getMapImproved();
        System.out.println(map.get("1"));
        map.remove("1");
        System.out.println(multiThreadError3.getMapImproved().get("1"));
    }
}
