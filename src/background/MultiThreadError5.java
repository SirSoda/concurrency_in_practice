package background;

/**
 * 隐式溢出-注册监听器
 * mxf
 * 2019年09月02日21:07:56
 */
public class MultiThreadError5 {

    int count;
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

    public MultiThreadError5(MySource mySource) {
        mySource.registerListener(new EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println("\n我得到的数字是" + count);
            }
        });
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new Event() {
                });
            }
        }).start();
        MultiThreadError5 multiThreadError5 = new MultiThreadError5(mySource);
    }
}
