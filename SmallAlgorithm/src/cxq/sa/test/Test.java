package cxq.sa.test;



	public class Test {
		
		public static int k=0;
		public static Test t1=new Test("t1");
		public static Test t2=new Test("t2");
		public static int i=print("i");
		public static int n=99;
		
		public int j=print("j");
		{
			print("构造块");
		}
		static{
			print("静态块");
		}
		
		public Test(String s){
			System.out.println((++k)+"构造函数:"+s+" i="+i+" n="+n);
			++i;++n;
		}
		public static int print(String s){
			System.out.println((++k)+":"+s+" i="+i+" n="+n);
			++n;
			return ++i;
		}
		/*
		 * 类加载之后,按从上到下执行被static修饰的语句,
		 * 如果有语句new了自身的对象,将从上到下执行构造代码块,
		 * 构造器,创建完对象后,接着执行下面的static语句
		 * */
		public static void main(String[] args){
			System.out.println("----main start-------");
			Test t=new Test("init");
			System.out.println("------main end-------");
		}
	}	


