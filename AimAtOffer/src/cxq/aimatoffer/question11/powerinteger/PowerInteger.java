package cxq.aimatoffer.question11.powerinteger;

public class PowerInteger {
    // 法1. normal
    public static double power1(double base, int exp) throws Exception {
        boolean isZero = (new Double(base)).equals(new Double(0)); 
        if (isZero && exp == 0) {
            throw new Exception("bad args");
        } else if (isZero) {
            return base;
        } else if (isZero && exp != 0) {
            return (double)0;
        } else if (exp == 0){
            return (double)1;
        } else { // exp < 0 || exp > 0
            double rst = 1;
            for (int i = 1; i <= Math.abs(exp); i++) { // 小心exp为负数
                rst *= base;
            }
            return exp > 0 ? rst : 1 / rst;
        }
    }
    
    // 法2. 公式法
    public static double power2(double base, int exp) throws Exception {
        boolean isZero = (new Double(base)).equals(new Double(0));
        int e = Math.abs(exp);
        
        if (isZero && e == 0) {
            throw new Exception("bad args");
        }
        if (isZero) { // 防止1/0
            return base;
        }
        if (e == 0) {
            return 1;
        }
        if (e == 1) {
            return base;
        }
        
        double rst = power2(base, e >> 1);
        rst *= rst;
        if ((e & 0x1) == 1) {
            rst *= base;
        }
        return exp < 0 ? 1 / rst : rst;
    }
    
    
    // for test
    public static void main(String[] args) {
        try {
            System.out.println(PowerInteger.power2(3, 2));
            System.out.println(PowerInteger.power2(0, 2));
            System.out.println(PowerInteger.power2(1, 0));
            System.out.println(PowerInteger.power2(2, -3));

            System.out.println(PowerInteger.power2(0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
