package cxq.sa.produceconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockGood<E> {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    
    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    private final List<E> list = new ArrayList<>();
    
    public void set(E o)
    {
        writeLock.lock();
        try
        {
            list.add(o);
            System.out.println("Adding element by thread"+Thread.currentThread().getName());
            }
        finally
        {
            writeLock.unlock();
        }
    }
    
    public E get(int i)
    {
        readLock.lock();
        try
        {
                        System.out.println("Printing elements by thread"+Thread.currentThread().getName());
            return list.get(i);
        }
        finally
        {
            readLock.unlock();
        }
    }
      public static void main(String[] args)
      {

      }
}
