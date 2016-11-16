package com.db.bms.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.db.bms.entity.HouseDb;
import com.db.bms.entity.People;

/**
 * xlsx service
 */
public interface XlsxService {

	/**
	 * get file path
	 * @param filename
	 * @return
	 */
	String getFilePath(String filename) throws Exception;
		
	HouseDb loadHouseDb(String filename) throws Exception;

	List<People> searchArmy(String filename) throws Exception;
	List<People> searchChildbearingWoman(String filename) throws Exception;
	List<People> searchRetired(String filename) throws Exception;
	List<People> searchSpecialGroup(String filename) throws Exception;
	List<People> searchByKeyword(String filename, String keyword) throws Exception;

	Workbook writeResultToXlsx(List<People> result) throws Exception;
	
}
