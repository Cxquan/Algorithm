package stablematching.people;

import java.util.ArrayList;

public class Girl {
	/* girl's name */
	private String name = null;
	/* girl's ranking list for all boys */
	private ArrayList<String> rankList = new ArrayList<String>();
	/* current girl-friend */
	private Boy partner = null;
	
	
	
	public Girl(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getRank(String boyName) {
		int i = 0;
		for (String name : rankList) {
			if (boyName.equals(name)) {
				return i;
			}
			i++;
		}
		System.err.printf("!!!error while get %s's rank from %s's rankList!!!"
				, boyName, this.getName());
		return -1;
	}
	
	public Boy getPartner() {
		return partner;
	}
	
	public void setPartner(Boy boy) {
		this.partner = boy;
	}
	
	public void putRankList(String name) {
		this.rankList.add(name);
	}
	
}
