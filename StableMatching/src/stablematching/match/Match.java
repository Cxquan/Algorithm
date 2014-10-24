package stablematching.match;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import stablematching.people.Boy;
import stablematching.people.Girl;

public class Match {

	public static void main(String[] args) {
		if (!checkArgs(args)) {
			return;
		}
		/* a list for all boys */
		ArrayList<Boy> boysList = new ArrayList<Boy>();
		/* a list for all girls */
		ArrayList<Girl> girlsList = new ArrayList<Girl>();
		/* a list for unmatched boys*/
		ArrayList<Integer> unMatchedBoys = new ArrayList<Integer>();
		
		/* read ranking files */
		doInit(args, boysList, girlsList, unMatchedBoys);
		/* start matching */
		doMatch(boysList, girlsList, unMatchedBoys);
		/* export matching result */
		doExportResult(args[2], boysList, girlsList);
	}
	
	public static void doMatch(ArrayList<Boy> boysList, ArrayList<Girl> girlsList, ArrayList<Integer> unMatchedBoys) {
		while (!unMatchedBoys.isEmpty()) {
			/* select one boy from unMatchedBoys list arbitrarily */
			Boy boy = pickOneBoy(unMatchedBoys, boysList);
			/* boy propose to girl */
			boy.doPropose(girlsList, unMatchedBoys);
		}
	}
	
	public static Boy pickOneBoy(ArrayList<Integer> unMatchedBoys, ArrayList<Boy> boysList) {
		Random random = new Random();
		int idx = random.nextInt(unMatchedBoys.size());
		return boysList.get(unMatchedBoys.get(idx));
	}
	
	public static boolean checkArgs(String[] args) {
		if (args.length != 3) {
			System.err.printf("!!!bad arguements!!!\nThere should be 3 arguements"
					+ "like £º\n     <path_of_boys> <path_of_girls> <path_to_result>\n");
			return false;
		}
		int i = 0;
		while(i<2) {
			File tmpFile = new File(args[i]);
			if (!tmpFile.exists()) {
				System.err.printf("!!!file does not exist : %s!!!\n", tmpFile);
				return false;
			}
			i++;
		}
		File tmpFile = new File(args[2]);
		if (tmpFile.isDirectory()) {
			System.err.printf("!!!result file name is missing %s!!!\n", tmpFile);
			return false;
		}
		return true;
	}
	
	public static void doInit(String[] args, ArrayList<Boy> boysList, 
			ArrayList<Girl> girlsList, ArrayList<Integer> unMatchedBoys) {		
		try {
			readBoysRankFile(args[0], boysList, unMatchedBoys);
			readGirlsRankFile(args[1], girlsList);
		} catch (Exception e) {
			System.err.printf("!!!error while reading rankings!!!\n");
			e.printStackTrace();
		}
	}
	
	public static void readBoysRankFile(String path, ArrayList<Boy> list,
			ArrayList<Integer> unMatchedBoys) throws IOException {
		File file = new File(path);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			Integer boysID = 0;
			while ((line = reader.readLine()) != null) {
				String rank[] = line.split(" ");
				String name = rank[0].replace(":", "");
				Boy boy = new Boy(name);
				for (int i = 1; i < rank.length; i++) {
					boy.putRankList(rank[i]);
				}
				list.add(boy);
				unMatchedBoys.add(boysID);
				boysID++;		
			}
		} catch (Exception e) {
			System.err.printf("!!!error while reading boys rankings!!!\n");
			e.printStackTrace();
		} finally {
			reader.close();
		}
	}
	
	public static void readGirlsRankFile(String path, ArrayList<Girl> list) throws IOException {
		File file = new File(path);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String rank[] = line.split(" ");
				String name = rank[0].replace(":", "");
				Girl girl = new Girl(name);
				for (int i = 1; i < rank.length; i++) {
					girl.putRankList(rank[i]);
				}
				list.add(girl);
			}
		} catch (Exception e) {
			System.err.printf("!!!error while reading girls rankings!!!\n");
			e.printStackTrace();
		} finally {
			reader.close();
		}
	}
	
	public static void doExportResult(String path, ArrayList<Boy> boysList, ArrayList<Girl> girlsList) {
		File out = new File(path);
		BufferedWriter writer = null;		
		try {
			writer = new BufferedWriter(new FileWriter(out));
			//writer.write("The matched pairs are listed like this:\n"
					//+ "<boy_name>(<partner's_rank>) - <girl_name>(<partner's_rank>)\n\n");
			for (Boy boy : boysList) {
				Girl girl = boy.getPartner();
				if (girl == null) {
					break;
				}
				String pair = boy.getName()+"("+boy.getRank(girl.getName())
						+") - "+girl.getName()+"("+girl.getRank(boy.getName())+")\n";
				writer.write(pair);
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
		System.out.printf("\nFinished Matching! Result is stored at %s", path);
	}
	
}
