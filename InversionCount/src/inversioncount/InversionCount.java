package inversioncount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InversionCount {

	public static void main(String[] args) {
		if (!checkArgs(args)) {
			return;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		doInit(args[0], list);
		Integer invCnt = QuickSortCount(list, 0, list.size());
		Integer invCnt = SortAndCount(list);
		
	}
	
	public static Integer sortAndCount(ArrayList<Integer> list) {
			if (list.size() == 1) {
				return 0;
			} else if (list.size() == 2){
				
			}
			
	}
	
	public static Integer QuickSortCount(ArrayList<Integer> list, int high, int low) {
		if (high-low == 0) {
			return 0;
		} else if (high-low == 1) {
			if (list.get(low) > list.get(high)) {
				swap(list.get(low), list.get(high));
				return 1;
			}
			return 0;			
		}
		int invCnt = 0;
		int mid = (high+low)/2; //floor ?
		for (int i=low, j=high;;) {
			
		}
	}
	
	public static void swap(Integer a, Integer b) {
		int tmp = a;
		a = b;
		b = tmp;
	}

	public static void doInit(String path, ArrayList<Integer> list) {		
		File file = new File(path);
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				Integer num = Integer.valueOf(line.trim());
				list.add(num);
			}
		} catch (Exception e) {
			System.err.printf("!!!error while reading input file!!!\n");
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.err.printf("!!!error while closing input file!!!\n");
				e.printStackTrace();
			}
		}
	}
	
	public static boolean checkArgs(String[] args) {
		if (args.length != 2) {
			System.err.printf("!!!bad arguements!!!\nThere should be 2 arguements"
					+ "like £º\n     <path_of_input_file> <path_of_result_file>\n");
			return false;
		}
		File tmpFile = new File(args[0]);
		if (!tmpFile.exists()) {
			System.err.printf("!!!input file does not exist : %s!!!\n", tmpFile);
				return false;
		}		
		tmpFile = new File(args[1]);
		if (tmpFile.isDirectory()) {
			System.err.printf("!!!result file name is missing %s!!!\n", tmpFile);
			return false;
		}
		return true;
	}

}
