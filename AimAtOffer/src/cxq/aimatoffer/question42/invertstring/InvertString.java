package cxq.aimatoffer.question42.invertstring;

public class InvertString {
  //invert words
    private static String revert(String sen) {
        if (sen==null || sen.length() ==0) return "";
//        int i = sen.length() -1;
//        StringBuffer sb = new StringBuffer();
//        while(i >= 0) {
//            sb.append(sen.charAt(i));
//            i--;
//        }
//        return sb.toString();
        return new StringBuilder(sen).reverse().toString();
    }
    public static String invertSentence(String sen) {
        String rsen = revert(sen);
        StringBuilder  rst = new StringBuilder();
        int i = 0;
        
        StringBuilder word = new StringBuilder("");
        while(i<rsen.length()){
            char a = rsen.charAt(i);
            if(a != ' ') {
                word.append(a);
            } else {
                rst.append(revert(word.toString())).append(" ");
//                word.delete(0, word.length());
                word = new StringBuilder("");
            }
            i++;
        }
        rst.append(revert(word.toString()));
        return rst.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(invertSentence("hello world"));

    }

}
