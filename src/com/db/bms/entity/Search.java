package com.db.bms.entity;

import com.alibaba.fastjson.JSON;


public class Search extends BaseModel implements java.io.Serializable  {

    public String filename;
    
    public static int SEARCH_TYPE_SEARCH=1;
    public static int SEARCH_TYPE_ARMY=2;
    public static int SEARCH_TYPE_CHILDBEARING_WOMAN=3;
    public static int SEARCH_TYPE_RETIRED=4;
    public static int SEARCH_TYPE_SPECIAL_GROUP=5;
    
    // if searchType is SEARCH_TYPE_SEARCH, must set keyword
    public Integer searchType;
    

    /** default constructor */
    public Search() {
    }

	public String toString() {
        return JSON.toJSONString(this);
    }

	// gettter and setter
	//-----------------------------------------------
	
    public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

}
