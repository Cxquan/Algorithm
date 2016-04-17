package cxq.sa.test;

import java.util.LinkedList;
import java.util.List;

public class Exam {

	// 装苹果的箱子，以布尔值为苹果
	static volatile	List<Boolean> box = new LinkedList<Boolean>();
	static final int SIZE = 5;
	
	class Putter extends Thread{

		@Override
		public void run() {
			while (true) {
				synchronized (box) {
					if (box.size() < SIZE) {
						box.add(true); // 放入一个苹果到箱子中
						System.out.println("放苹果");
					}
					box.notify();
					
				}
			}
		}
	}
	
	class Getter extends Thread {
		
		@Override
		public void run() {
			while (true) {
				synchronized (box) {
					while(box.size()==0) {
						try {
							box.wait(); // 释放box锁，进入阻塞态
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					box.remove(0); // 取出一个苹果
					System.out.println("取苹果");
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		Exam exam = new Exam();
		Putter putter = exam.new Putter();
		Getter getter = exam.new Getter();
		
		putter.start();
		getter.start();
	}

	
	

}
