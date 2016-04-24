package cxq.sa.multithread;

//结论：
//1、当前线程调用类的同步静态方法时，其它线程可以进入该类实例的其它非静态方法，不能进入类的其它静态方法。
//2、同理，当前进入同步的实例方法时，其它线程可以进入该类的静态方法，但是不能进入该类的其它非静态方法。
//原因：静态方法同步是获取StaticSynTest.class锁，而实例方法获取的是该“实例对象”的锁，它们互不干涉。
//一个非同步方法总是可以被调用而不会有任何问题。实际上，Java 没有为非同步方法做任何检查，锁对象仅仅在同步方法或者同步代码块中检查。

class StaticSync implements Runnable {
	static boolean flag = true;

	public static synchronized void test0() {// 同步监视器是该类本身
		for (int i = 0; i < 1000; i++) {
			System.out.println("test0: " + Thread.currentThread().getName()
					+ " " + i);
		}
	}

	public synchronized void test1() {// 同步监视器是this，即调用该方法的Java对象。
		for (int i = 0; i < 1000; i++) {
			System.out.println("test1: " + Thread.currentThread().getName()
					+ " " + i);
		}
	}

	public void run() {
		if (flag) {
			flag = false;
			test0();
		} else {
			flag = true;
			test1();
		}
	}

	public static void main(String args[]) throws InterruptedException {
		StaticSync ss = new StaticSync();
		StaticSync ss1 = new StaticSync();
		new Thread(ss).start();
		Thread.sleep(1);
		new Thread(ss1).start();
	}
}
