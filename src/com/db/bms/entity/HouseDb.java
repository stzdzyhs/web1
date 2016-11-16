package com.db.bms.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HouseDb extends HashMap<String, List<People>> {
	
	/**
	 * key order list.
	 */
	public List<String> addressOrder = new ArrayList<String>();
	
	public void addPeople(People p) {
		List<People> members = this.get(p.address);
		if(members==null) {
			members = new ArrayList<People>();
			addressOrder.add(p.address);
			this.put(p.address,  members);
		}
		members.add(p);
	}

	public void clear() {
		this.clear();
		addressOrder.clear();
	}
	
	
	public String toString() {
		int ret = 0;
		StringBuilder sb = new StringBuilder();
		for(String addr:addressOrder) {
			List<People> members = this.get(addr);
			for(People m:members) {
				sb.append(m.toString());
				sb.append("\n");
			}
			
			ret += members.size();
		}
		sb.append(String.format("addr count:%d, people:%d \n", this.addressOrder.size(), ret));
		return sb.toString();
	}
}
