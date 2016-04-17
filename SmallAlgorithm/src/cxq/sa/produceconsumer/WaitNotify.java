package cxq.sa.produceconsumer;

import java.util.List;
import java.util.ArrayList;

public class WaitNotify {
    private List<Boolean> warehouse = new ArrayList<Boolean>();
    final private int capacity;
    
    public WaitNotify(int cap) {
        this.capacity = cap;
    }
    
    public void get() {
        synchronized (warehouse) {
            while (warehouse.size() == 0) {
                try {
                    warehouse.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            warehouse.remove(0);
            warehouse.notify();
            System.out.println("---------get one");
        }
    }
    
    public void put() {
        synchronized (warehouse) {
            while (warehouse.size() >= capacity) {
                try {
                    warehouse.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            warehouse.add(true);
            warehouse.notify();
            System.out.println("put one----------");
        }
    }
    
    public class Producer implements Runnable {
        private final WaitNotify warehouse;
        
        public Producer(WaitNotify wh) {
            this.warehouse = wh;
        }
        @Override
        public void run() {
            while (true) {
                this.warehouse.put();
//                try {
//                    Thread.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
    
    public class Consumer implements Runnable {
        final WaitNotify warehouse;
        
        public Consumer(WaitNotify warehouse) {
            this.warehouse = warehouse;
        }
        
        @Override
        public void run() {
            while(true) {
                warehouse.get();
//                try {
//                    Thread.sleep(2);
//                } catch (InterruptedException e){
//                    e.printStackTrace();
//                }
            }
        }
    }

    public static void main(String[] args) {
        WaitNotify warehouse = new WaitNotify(3);
        Consumer c = warehouse.new Consumer(warehouse);
        new Thread(c).start();
        new Thread(c).start();
        new Thread(c).start();
        new Thread(c).start();
        new Thread(c).start();
        new Thread(c).start();
        Producer p = warehouse.new Producer(warehouse);
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        System.out.println("Ctrl+c to stop it.");
    }
}
