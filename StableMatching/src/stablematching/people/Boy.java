package stablematching.people;

import java.util.ArrayList;

public class Boy {
	/* boy's name */
	private String name = null;
	/* boy's ranking list for all girls */
	private ArrayList<String> rankList = new ArrayList<String>();
	/* current girl-friend */
	private Girl partner = null;
	/* rank of last girl this boy proposed to */
	private Integer lastProposedGirl = -1;
	
	public Boy(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getRank(String girlName) {
		int i = 0;
		for (String name : rankList) {
			if (girlName.equals(name)) {
				return i;
			}
			i++;
		}
		System.out.printf("!!!error while get %s's rank from %s's rankList!!!"
				, girlName, this.getName());
		return -1;
	}

	public Girl getPartner() {
		return partner;
	}
	
	public void setPartner(Girl girl, ArrayList<Integer> unMatchedBoys) {
		/* set new girl-friend */
		this.partner = girl;
		
		/* remove boy's name from unMatchedBoys list */
		for (int i = 0; i < unMatchedBoys.size(); i++) {
			if (this.getName().equals("B"+unMatchedBoys.get(i))) {
				unMatchedBoys.remove(i);
				return;
			}
		}
		System.err.printf("!!!can not find %s in unMatchedBosyList!!!\n", this.getName());
	}
	
	public void setPartner(String none) {
		/* dumped by his ex-girl-friend */
		this.partner = null;
		return;
	}
	
	public void putRankList(String name) {
		this.rankList.add(name);
	}
	
	public void doPropose(ArrayList<Girl> girlsList, ArrayList<Integer> unMatchedBoys) {
		Girl girl = getNextGirl(girlsList);
		if (girl.getPartner() == null) {
			this.setPartner(girl, unMatchedBoys);
			girl.setPartner(this);
		} else if (girl.getRank(this.name) < girl.getRank(girl.getPartner().getName())) {
			Boy sadBoy = girl.getPartner();
			/* add into umMatchedBoys list */
			sadBoy.setUnmatched(unMatchedBoys);
			girl.setPartner(this);
			this.setPartner(girl, unMatchedBoys);
		} else {
			/* rejected by girl */
		}
	}
	
	private Girl getNextGirl(ArrayList<Girl> girlsList) {
		String girlName = rankList.get(++lastProposedGirl);
		int girlID = Integer.valueOf(girlName.substring(1));
		return girlsList.get(girlID);
	}
	
	private void setUnmatched(ArrayList<Integer> unMatchedBoys) {
		unMatchedBoys.add(Integer.valueOf(this.name.substring(1)));
	}
}
