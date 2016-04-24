package cxq.sa.syntax;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.LinkedList;
public class MemAllocate {
    private static List<int[]> staticVar = new LinkedList<>();
    private static int[] a = new int[99999]; // 分配在heap中，不是permGen
    
    
    public void genOOM1() {
        while(true) {
            Map<String, String> a = new HashMap<>();
            a.put(null, null);
//            staticVar.add(new int[99]);
        }
    }
    
    /*
     * jdk7中字符串常量池移出permGen,放到了heap中
     */
    public void genOOM2() {
        int i = 0;
        String a,b;
        while(++i > 0) {
            a = "hello"+i;
            b = a.intern();
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
           MemAllocate s = new MemAllocate();
           s.genOOM1();
//           s.genOOM2();
    }

}
