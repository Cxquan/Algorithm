package cxq.aimatoffer.question8.minnumberinrotatearray;

public class MinNumberInRotateArray {
    public static int getMinInRotateArray(int[] array) throws Exception {
        if (array == null || array.length == 0) {
            throw new Exception("invalid arguements");
        }
        int left = 0;
        int right = array.length - 1;
        
        while (left < right) {
            if (right == left + 1) {
                break;
            }
            int m = (left + right) / 2;
            int mid = array[m];
            
            if (array[left] == mid && mid == array[right]) {
                return min(array);
            }
            if (mid <= array[right]) { 
                // 考虑没有rotate，全部升序情况，左边先比较会bug，因此右边优先比较
                right = m;
            } else if (mid >= array[left]) {
                left = m; 
            } else {
                // array[right] > mid > array[left]，此时数组不是rotate array
                throw new Exception("invalid arguements");
            }
        }
        return array[right] < array[left] ? array[right] : array[left]; 
    }
    
    private static int min(int[] array) {
        assert array != null && array.length > 0;
        int min = Integer.MAX_VALUE;
        for (int i : array) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }
    
    // for test
    public static void main(String[] args) {
        int[] array = {3, 4, 5, 1, 0}; // 非rotate数组
//        {1, 2, 3, 4, 5};
//        {1, 1, 1, 0, 1, 1};
        int min;
        try {
            min = MinNumberInRotateArray.getMinInRotateArray(array);
            System.out.println(min);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
