package cxq.aimatoffer.question20.printmatrix;

public class PrintMatrix {
    public static void printClockWisely(int[][] matrix, int row, int col) {
        if (matrix == null || row <= 0 || col <= 0) {
            System.out.println("bad args");
            return;
        }
        // validate row and col
        
        int left = 0;
        int right = col - 1;
        int up = 0;
        int down = row - 1;
        int count = 0;
        int total = row * col;
        StringBuffer sb = new StringBuffer();
        while (count < total) {
            if (left <= right && count < total) {
                for (int i = left; i <= right; i++) {
                    sb.append(matrix[up][i]);
                    count += 1;
                }
                up += 1;
            }
            if (up <= down && count < total) {
                for (int i = up; i <= down; i++) {
                    sb.append(matrix[i][right]);
                    count += 1;
                }
                right -= 1;
            }
            if (right >= left && count < total) {
                for (int i = right; i >= left; i--) {
                    sb.append(matrix[down][i]);
                    count += 1;
                }
                down -= 1;
            }
            if (up <= down && count < total) {
                for (int i = down; i >= up; i --) {
                    sb.append(matrix[i][left]);
                    count += 1;
                }
                left += 1;
            }
        }
        System.out.println(sb.toString());
    }
    
    // for test
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3}};
        PrintMatrix.printClockWisely(matrix, 1, 3);
    }
}
