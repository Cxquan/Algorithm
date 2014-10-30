package inversioncount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class InversionCount {

	public static void main(String[] args) {
		
		if (!checkArgs(args)) {
			return;
		}
		ArrayList<Integer> rawList = new ArrayList<>();
		ArrayList<Integer> sortedList = new ArrayList<>();
		doInit(args[0], rawList);
		
		/* sort_and_count */
		long start = System.currentTimeMillis();
		BigInteger rc = sort_and_count(rawList, 0, rawList.size()-1, sortedList);
		long time = System.currentTimeMillis() - start;
		doExportResult(args[1], rc, sortedList, time);
		String tmp = "\nFinished counting with sort_and_count! \n"
				+ "RC = " + rc + ";  timecost = " + time + " millisecond.  "
				+ "Result is stored at " + args[1];
		System.out.print(tmp);
		
		/*quick_sort_count*/
		start = System.currentTimeMillis();
		rc = quick_sort_Count(rawList, 0, rawList.size()-1);
		time = System.currentTimeMillis() - start;
		doExportResult(args[2], rc, rawList, time);
		tmp = "\n\nFinished counting with quick_sort_count! \n"
				+ "RC = " + rc + ";  timecost = " + time + " millisecond.  "
				+ "Result is stored at " + args[2];
		System.out.print(tmp);
	}
	
	public static BigInteger sort_and_count(ArrayList<Integer> rawList, int low, int hight,
			ArrayList<Integer> sortedList) {
		if (hight - low <= 0) {
			sortedList.add(rawList.get(low));
			return BigInteger.ZERO;
		}
		int mid = (hight + low) / 2;
		ArrayList<Integer> leftList = new ArrayList<Integer>();
		ArrayList<Integer> rightList = new ArrayList<Integer>();
		
		BigInteger rcLeft = sort_and_count(rawList, low, mid, leftList);
		BigInteger rcRight = sort_and_count(rawList, mid+1, hight, rightList);
		
		return rcLeft.add(rcRight).add(merge_and_count(leftList, rightList, sortedList));
	}
	
	public static BigInteger merge_and_count(ArrayList<Integer> leftList, 
			ArrayList<Integer> rightList, ArrayList<Integer> mergedList) {
		BigInteger rc = BigInteger.ZERO;
		int lLen = leftList.size();
		int rLen = rightList.size();
		int i= 0;
		int j=0;
		
		while (i<lLen && j<rLen) {
			if (leftList.get(i) > rightList.get(j)) {
				mergedList.add(rightList.get(j));
				long tmp = (lLen+rLen)%2 == 0 ? ((lLen+rLen)/2 - i) : ((lLen+rLen)/2 - i + 1) ;
				rc = rc.add(BigInteger.valueOf(tmp));
				j++;
				if (j==rLen) {
					mergedList.add(leftList.get(i++));
				}
			} else {
				mergedList.add(leftList.get(i++));
			}
		}
		while (i < lLen) {
			mergedList.add(leftList.get(i++));
		}
		while (j < rLen) {
			mergedList.add(rightList.get(j++));
		}		
		return rc;
	}
	
	public static BigInteger quick_sort_Count(ArrayList<Integer> rawList, int low, int high) {
		if (low >= high) {
			return BigInteger.ZERO;
		}
		ChgInteger mid = new ChgInteger((low+high)/2);
		BigInteger rc = merge_count(rawList, mid, low, high);
		int imid = mid.getInteger(); 
		rc = rc.add(quick_sort_Count(rawList, low, imid-1));
		rc = rc.add(quick_sort_Count(rawList, imid+1, high));
		return rc;
	}
	
	public static BigInteger merge_count(ArrayList<Integer> rawList, ChgInteger mid, int low, int high) {
		int imid = mid.getInteger();
		if (low > imid || imid > high) {
			return BigInteger.ZERO;
		}
		ArrayList<Integer> llList = new ArrayList<>();
		ArrayList<Integer> lrList = new ArrayList<>();
		ArrayList<Integer> rlList = new ArrayList<>();
		ArrayList<Integer> rrList = new ArrayList<>();
		int midValue = rawList.get(imid);
		BigInteger rc = BigInteger.ZERO;
		
		//left
		if (low < imid) {
			for (int i = imid-1; i >= low; i--) {
				if (rawList.get(i) <= midValue) {
					llList.add(rawList.get(i));
				} else {
					lrList.add(rawList.get(i));
					rc = rc.add(BigInteger.valueOf(llList.size()).add(BigInteger.ONE));
				}
			}
		}
		
		//right
		if (high > imid) {
			for (int i = imid+1; i <= high; i++) {
				if (rawList.get(i) >= midValue) {
					rrList.add(rawList.get(i));					
				} else {
					rlList.add(rawList.get(i));
					rc = rc.add(BigInteger.valueOf(rrList.size()).add(BigInteger.ONE));
				}
			}
		}
		rc = rc.add(BigInteger.valueOf(lrList.size()*rlList.size()));
		// merge
		int k = low;
		for (int i=llList.size()-1; i>=0; i--) {
			rawList.set(k++, llList.get(i));
		}
		for (Integer integer : rlList) {
			rawList.set(k++, integer);
		}		
		imid = k;
		rawList.set(k++, midValue);
		mid.setInteger(imid);
		for (int i = lrList.size()-1; i >= 0; i--) {
			rawList.set(k++, lrList.get(i));
		}
		for (Integer integer : rrList) {
			rawList.set(k++, integer);
		}
		return rc;
	}

	public static void doInit(String path, ArrayList<Integer> list) {		
		File file = new File(path);
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if ((line=line.trim()).isEmpty()) {
					continue;
				}
				Integer num = Integer.valueOf(line);
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
		if (args.length != 3) {
			System.err.printf("!!!bad arguements!!!\nThere should be 3 arguements"
					+ "like £º\n     <path_of_input_file> <path_of_result_file_for_sortAndCount>"
					+ " <path_of_result_file_for_quickSortCount>\n");
			return false;
		}
		File tmpFile = new File(args[0]);
		if (!tmpFile.exists()) {
			System.err.printf("!!!input file does not exist : %s!!!\n", tmpFile);
				return false;
		}
		int k = 1;
		while (k < 3) {
			tmpFile = new File(args[k++]);
			if (tmpFile.isDirectory()) {
				System.err.printf("!!!result file name is missing %s!!!\n", tmpFile);
				return false;
			}
		}
		return true;
	}
	
	public static void doExportResult(String path, BigInteger rc, ArrayList<Integer> sortedList, long time) {
		File out = new File(path);
		BufferedWriter writer = null;		
		try {
			writer = new BufferedWriter(new FileWriter(out));
			String tmp = "the inversion number is: " + rc +"\n"
					+"spend "+time+" millisecond to count it.\n"
					+"numbers are sorted as below:\n";
			writer.write(tmp);
			for (Integer num : sortedList) { 
				writer.write(num.toString()+"\n");
			}
			writer.flush();
		} catch (Exception e) {
			System.err.printf("!!!error while exporting result!!!\n");
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e2) {
				System.err.printf("!!!error while closing bufferedWriter!!!\n");
				e2.printStackTrace();
			}
		}
	}
}
