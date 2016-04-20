package cxq.sa.produceconsumer;

import java.util.LinkedList;
import java.util.List;

/*
 * only use synchronized
 * 不是严格意义上的生产者消费者，生产者遇到满仓没有等待而是退出，并且每次调用都需要先获取锁，不管算仓库里是否是空的还是满的
 * 并且，线程只有在等待锁
 */
@Deprecated
public class SyncWarehouseDeprecated {
    final int MAX_SIZE = 3;
    private List<Boolean> warehouse = new LinkedList<Boolean>();
    
    public SyncWarehouseDeprecated() {}
    
    public void put(int num) {
        if (num <= 0) {
            return;
        }
        synchronized (warehouse) { // 缺点：要是一次放多个apple，必须跳出循环才能释放锁
            while (warehouse.size() < this.MAX_SIZE && num > 0) {
                warehouse.add(true);
                System.out.println("-------add an apple");
                num -= 1;
            }
        }
    }
    
    public void get(int num) {
        if (num <= 0) {
            return;
        }
        synchronized (warehouse) {
            while (warehouse.size() > 0 && num > 0) {
                warehouse.remove(0);
                num -= 1;
                System.out.println("remove an apple---------");
            }
        }
    }

    /* 
     * producer
     * 
     */
    public class Producer extends Thread {
        private int num = 1;
        // 其实内部类可直接通过SyncWarehouse.this获取到外围对象(参考Consumer的实现)，但是本题只是为了方便写成内部类
        // 实际上Producer/Consumer和Warehouse应该是分开的三个类
        final private SyncWarehouseDeprecated swh;

        public Producer(SyncWarehouseDeprecated swh, int num) {
            this.swh = swh;
            this.num = num;
        }

        @Override
        public void run() {
            while (true) {
                this.swh.put(num);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /*
     * consumer
     */
    public class Consumer extends Thread {
        private int num = 1;
//        final private SyncWarehouse swh;  
        
        public Consumer(int num) { //SyncWarehouse swh) {
//            this.swh = swh;
            this.num = num;
        }
        
        @Override
        public void run() {
            while (true) {
                // 内部类访问外围类对象的实现方式
                SyncWarehouseDeprecated.this.get(num);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    static public void main(String[] args) {
        SyncWarehouseDeprecated wn = new SyncWarehouseDeprecated();
        SyncWarehouseDeprecated.Producer p1 = wn.new Producer(wn, 1);
        SyncWarehouseDeprecated.Consumer c1 = wn.new Consumer(1);
        SyncWarehouseDeprecated.Producer p2 = wn.new Producer(wn, 1);
        SyncWarehouseDeprecated.Consumer c2 = wn.new Consumer(1);
        p1.start();
        c1.start();
        p2.start();
        c2.start();
    }

}
