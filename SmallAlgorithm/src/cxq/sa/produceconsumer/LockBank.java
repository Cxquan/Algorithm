package cxq.sa.produceconsumer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBank {
    // volatile为了保证变量对线程的可见性，happens-before规则
    // 但是，Lock的规范本身就有可见性要求，java语言规范中的要求
    // 并且synchronized 也具有可见性, 因此volatile不必要
    // Condition可用于条件await和signalAll()
//    private volatile long money = 0; 
    private long money = 0; 
    private Lock lock = new ReentrantLock();
    private static final ThreadLocal<Integer> tl = new ThreadLocal<Integer>();
    
    public boolean get(long num) {
        if (money < num) {
            System.out.println("=====no enough money: " + this.money);
            return false;
        }
        // 必须使用finally释放lock
        lock.lock();  // 放在try前面，因为放里面的话finally需要检查是否有获取到锁，没有的话会抛出
                        //IllegalMonitorException
        try {
            money -= num;
            System.out.println("------get money");
        } finally { 
            lock.unlock();
        }
        return true;
    }
    
    public boolean put(long num) {
        lock.lock();
        try {
            money += num;
            System.out.println("put money------");
        } finally {
            lock.unlock();
        }
        return true;
        
    }
    
    public class Consumer implements Runnable {
        private final LockBank bank;
        
        public Consumer(LockBank bank) {
            this.bank = bank;
        }
        
        @Override
        public void run() {
            while(true) {
                bank.get(20);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class Producer implements Runnable {
        final private LockBank bank;
        
        public Producer(LockBank bank) {
            this.bank = bank;
        }
        
        @Override
        public void run() {
            while(true) {
                bank.put(10);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        LockBank bank = new LockBank();
        Consumer c = bank.new Consumer(bank);
        Producer p = bank.new Producer(bank);
        
        new Thread(c).start();
        new Thread(c).start();
        new Thread(c).start();
        new Thread(c).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
    }

}
