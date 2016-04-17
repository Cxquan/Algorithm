package cxq.aimatoffer.question29.morethanhalf;

public class Morethanhalf {

    public static boolean validResult(int[] input, int num) {
        int cnt = 0;
        for (int a : input) {
            if (a == num) {
                cnt += 1;
            }
        }
        return cnt > (input.length >> 2) ? true : false;
    }
    public static int half(int[] input) {
        if (input == null || input.length == 0) {
            System.out.println("bad args");
            return 0;
        }
        int num = 0;
        int count = 1;
        for (int i=0; i<input.length-1; i++) {
            if(count==0) {
                num = input[i];
                count += 1;
                continue;
            } else if (input[i] != num) {
                count -= 1;
            } else { // input == num
                count += 1;
            }
        }
        if (!validResult(input, num)) {
            System.out.println("invalid input");
            num = 0;
        }
        return num;
    }
    
    public static void main(String[] args) {
        int[] input = {4, 49,13, 49, 49, 27, 38, 49, 49};
        System.out.println(half(input));
    }
}
