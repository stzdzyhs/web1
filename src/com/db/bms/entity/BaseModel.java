package com.db.bms.entity;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.db.bms.utils.core.PageUtil;

/**
 * <b>Application describing:</b>模型-处理分页查询功能 <br>
 * <b>Date:</b>2014-7-9<br>
 * @version $Revision$
 */
public abstract class BaseModel extends Object {

	@JSONField(serialize=false)
	public String sortKey;
	@JSONField(serialize=false)
	public String sortType;

	@JSONField(serialize=false)
	public String keyword;

	@JSONField(serialize=false)
	public String path;
	@JSONField(serialize=false)
	public String searchPath;
	@JSONField(serialize=false)
	public List<Long> allocResourceIds;

	/**
	 * 
	 * <br>
	 * <b>功能：</b>分页实现<br>
	 * <b>作者：</b>wht<br>
	 * <b>日期：</b> 2013-7-9 <br>
	 * 
	 * @param navigate
	 */
	@JSONField(serialize=false)
	private PageUtil pageUtil;
	
	@JSONField(serialize=false)
	public PageUtil getPageUtil() {
		if (pageUtil == null)
			pageUtil = new PageUtil();
		return pageUtil;
	}

	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

	public String getSortKey() {
		return sortKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public String getKeyword() {
		if (keyword != null) {
			keyword = keyword.trim();
		}
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSearchPath() {
		return searchPath;
	}

	public void setSearchPath(String searchPath) {
		this.searchPath = searchPath;
	}

	public List<Long> getAllocResourceIds() {
		return allocResourceIds;
	}

	public void setAllocResourceIds(List<Long> allocResourceIds) {
		this.allocResourceIds = allocResourceIds;
	}

	public String toString() {
		String ret = JSON.toJSONString(this);
		return ret;
	}

}
