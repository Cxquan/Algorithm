package cxq.sa.syntax;

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
		String[] command = {"python", "-h"};
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		test.finalize();
		
//		test.tOrF();
		
//		test.countN2(9, 5);
		
		
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
