package cxq.sa.produceconsumer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * java读写锁实现，有1种方式：x.普通的ReentrantLock(bad) x.使用Condition；3.ReadWriteReentrantLock
 * 以下实现有bug，一个线程获取的锁无法在另一个线程中释放
 */
@Deprecated
public class ReadWriteLockDeprecated {
    private final StringBuffer sb;
    private final Lock fl = new ReentrantLock(); // 读优先
    private final Lock rcl = new ReentrantLock();
    private int rc = 0;

    private final Lock rl = new ReentrantLock();

    public ReadWriteLockDeprecated(String sb) {
        this.sb = new StringBuffer(sb);
    }
    
    public void write(String b) {
//        rl.lock(); // 写优先
        fl.lock();
        try {
            this.sb.append(b);
        } finally {
            fl.unlock();
//            rl.unlock();
        }
    }
    
    public void read() {
//        rl.lock(); // 写优先
        rcl.lock();
        try {
            rc += 1;
            if (rc == 1) {
                System.out.println("--------lock" + Thread.currentThread());
                fl.lock();
            }
        } finally {
            rcl.unlock();
//            rl.unlock(); // 写优先
        }
        System.out.println("Cnt: " + this.rc + " sb:" + this.sb.toString());
        rcl.lock();
        try {
            rc -= 1;
        } finally {
            if (rc <= 0) {
                System.out.println("unlock------------" + Thread.currentThread());
                fl.unlock(); // !!!!!!! BUG !!!!!! IllegalMonitorStateException 
            }
            rcl.unlock();
        }
    }
    
    public class Writer implements Runnable {
        private final ReadWriteLockDeprecated file;
        public Writer(ReadWriteLockDeprecated file) {
            this.file = file;
        }
        
        @Override
        public void run() {
            while(true){
                file.write("-world-");
            }
        }
    }
     public class Reader implements Runnable {
        private final ReadWriteLockDeprecated file;
        public Reader(ReadWriteLockDeprecated file) {
            this.file = file;
        }
        
        @Override
        public void run() {
            while(true){
                file.read();
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }   

    public static void main(String[] args) {
        // 不好验证, 在此就不纠结了
        ReadWriteLockDeprecated file = new ReadWriteLockDeprecated("hello");
        Writer w = file.new Writer(file);
        Reader r = file.new Reader(file);
        new Thread(r, "r-1").start();
        new Thread(r, "r-2").start();
        new Thread(r, "r-3").start();
//        new Thread(w, "w-1").start();
    }
}
