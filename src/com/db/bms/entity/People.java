package com.db.bms.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

public class People extends BaseModel implements Serializable {
	public String skylineId;
	public String id;
	public String aid;
	public String name;
	public String sex;
	public Date birthday;
	public String cid;
	public String address;
	public String houseAddress;
	public String tel;
	public Date inDate;
	public String comment;
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	/**
	 * get age.
	 * @return null if can not get age.
	 */
	public Integer getAge() {
		Integer by = null;
		if(!StringUtils.isEmpty(this.cid)) {
			by = Integer.parseInt(this.cid.substring(6,10),10); 
		}
		else if(this.birthday!=null) {
			Calendar c1 = Calendar.getInstance();
			c1.setTimeInMillis(this.birthday.getTime());
			by = c1.get(Calendar.YEAR);
		}
		
		if(by==null) {
			return null;
		}
		
		Calendar now = Calendar.getInstance();
		by = now.get(Calendar.YEAR)- by ;
		return by;
	}
	
	
	public String getSkylineId() {
		return skylineId;
	}
	public void setSkylineId(String skylineId) {
		this.skylineId = skylineId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}	

}
