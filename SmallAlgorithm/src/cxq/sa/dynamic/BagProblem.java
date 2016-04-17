package cxq.sa.dynamic;

public class BagProblem {
  //01背包,要打印结果
    public static void bag01(int[] volume, int[] weight, int cap) {
        if(volume == null || weight==null||volume.length != weight.length) return; // simple check
        int[][] f = new int[weight.length+1][cap+1];
        // init
        for (int i=0; i<cap+1; i++) {
            f[0][i] = 0; // 不必恰好装满
        }
        for (int i=0; i<weight.length+1; i++) {
            f[i][0] = 0;
        }
        
        // fill matrix f
        for (int i=1; i<=weight.length; i++) {
            for (int j=0; j<cap+1; j++) {
                if(j-volume[i-1]<0){
                    f[i][j] = f[i-1][j];
                } else {
                    f[i][j] = Math.max(f[i-1][j], f[i-1][j-volume[i-1]] + weight[i-1]);
                }
            }
        }
    
        System.out.println("max value: " + f[weight.length][cap]);
        // trace back
        int v = cap;
    
        for (int i=weight.length; i>0; i--) {
            if(f[i][v]==f[i-1][v]) {
                continue;
            } else {
                System.out.println("has put: " + i);
                v -= volume[i-1];
            }
        }
    } 
    
    public static void main(String[] args) {
        bag01(new int[]{1,3,4}, new int[]{3,1,2}, 5);
    }
    
}
