package background;

/**
 * 使用工厂模式修复对象还没有创建完成就进行赋值的泄漏问题
 * mxf
 * 2019年09月03日21:10:09
 */
public class MultiThreadError5Fix {
    int count;

    private EventListener eventListener;
    interface Event{

    }

    interface EventListener{
        void onEvent(Event event);
    }

    static class MySource{
        private EventListener listener;

        void registerListener(EventListener listener) {
            this.listener = listener;
        }

        void eventCome(Event event) {
            if(listener != null) {
                listener.onEvent(event);
            }else {
                System.out.println("还未初始化完毕");
            }
        }
    }

    private MultiThreadError5Fix(MySource mySource) {
        eventListener = new EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println("\n我得到的数字是" + count);
            }
        };
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static MultiThreadError5Fix getInstance(MySource mySource) {
        MultiThreadError5Fix multiThreadError5Fix = new MultiThreadError5Fix(mySource);
        mySource.registerListener(multiThreadError5Fix.eventListener);
        return multiThreadError5Fix;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new Event() {
                });
            }
        }).start();
        MultiThreadError5Fix multiThreadError5Fix = MultiThreadError5Fix.getInstance(mySource);
    }
}
