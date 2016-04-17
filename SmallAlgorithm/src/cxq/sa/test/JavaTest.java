package cxq.sa.test;

import java.io.IOException;
import java.math.BigDecimal;


public class JavaTest {

	public static void main(String[] args) {
		JavaTest test = new JavaTest();
		int i = 2;
		i = i<<1;
		System.out.printf("result of 2<<3 = %s\n", 2<<3);
		System.out.println(Integer.parseInt((new BigDecimal("2.3").setScale(0, BigDecimal.ROUND_HALF_UP)).toString()));
		System.out.println(Integer.parseInt((new BigDecimal("2.5").setScale(0, BigDecimal.ROUND_HALF_UP)).toString()));
		String[] command = {"python"};
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		test.finalize();
		
//		test.tOrF();
		
//		test.countN2(9, 5);
		
//		test.lcs();
		
//		test.maxMatrix();
		
		
	}
	
	
	
	// �����������
	public void maxMatrix() {
		final int[] heigh = {4,5,3,2,3};
		
		int[] left = new int[heigh.length];
		int[] right = new int[heigh.length];
	
		left[0] = 1;
		for (int i = 1; i < heigh.length; i++) {
			left[i] = 1;
			if (heigh[i] == heigh[i-1]) {
				left[i] = left[i-1] +1;
			} else if (heigh[i] > heigh[i-1]){
				left[i] = 1;
			} else {
				int p = i-1;
				while(p >=0 && heigh[i] <= heigh[p]) {
					left[i] += left[p];
					p = p - left[p];
				}
			}			
		}
		
		right[heigh.length-1] = 1;
		for (int i = heigh.length-2; i > -1; i--) {
			right[i]= 1;
			if (heigh[i] == heigh[i+1]) {
				right[i] = right[i+1] +1;
			} else if (heigh[i] > heigh[i+1]){
				right[i] = 1;
			} else {
				int p = i+1;
				while(p < heigh.length && heigh[i] <= heigh[p]) {
					right[i] += right[p];
					p = p + right[p];
				}
			}			
		}
		
		long maxMatrix = 0;
		for (int i = 0; i < heigh.length; i++) {
			System.out.println(left[i] + " " + right[i]);
			long tmp = (left[i] + right[i] -1) * heigh[i];
			maxMatrix = maxMatrix > tmp ? maxMatrix : tmp;
		}
		System.out.print("max matrix space = " + maxMatrix);
	}
	
	// �����������
	public void lcs() {
		String aString = "astringaa";
		String bString = "bstringabbb";
		int row = aString.length();
		int column = bString.length();
		int[][] matrix = new int[row+1][column+1];
		
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= column; j++) {
				if (aString.charAt(i-1) == bString.charAt(j-1)) {
					matrix[i][j] = matrix[i-1][j-1] +1;
				} else {
					matrix[i][j] = matrix[i-1][j]>matrix[i][j-1] ? matrix[i-1][j] : matrix[i][j-1];
				}
			}
		}
		System.out.print("max lcs = " + matrix[row][column]);
	}
	
	// Լɪ�����⣬�����һ���˵�ԭʼ���
	public void countN(int n, int m) {
		int num = 1;
		for (int i = 2; i <=n; i++) {
			num = (num + m) % n;
			if (num==0) num = i;
		}
			System.out.print(" "+num);
	}
	
	// Լɪ�����⣬�����г��е��˵�ԭʼ���
	static class Person {
		int code;
		Person next;
		public Person(int i) {
			code = i;
		}
	}
	public void countN2(int n, int m) {
		Person a1 = new Person(1); 
		Person tmpPerson = a1;
		for (int i = 2; i <= n; i++) {
			tmpPerson.next = new Person(i);
			tmpPerson = tmpPerson.next;
		}
		tmpPerson.next = a1;

		while(tmpPerson.next != tmpPerson) {
			for(int i=1; i<m; i++){
				tmpPerson = tmpPerson.next;
			}
			System.out.print(tmpPerson.next.code+" ");
			tmpPerson.next = tmpPerson.next.next;
		}
		System.out.print(tmpPerson.code);
	}
	
	@Override
	public void finalize() {
		System.out.println("finalize");
	}
	
	public void tOrF() {
		Integer a = new Integer(10);
		Integer aa = new Integer(10);
		Long b = new Long(10);
		Double c = new Double(10.0);
		
//		System.out.println(a==b);
//		System.out.println(a==c);
		System.out.println(a==10.0 ); //ǿ������ת����
		System.out.println(a.equals(aa));
		System.out.println(a == aa);
		System.out.println(a.equals(b));
		System.out.println(a.equals(c));
		System.out.println(c.equals(10.0));
	}

}
