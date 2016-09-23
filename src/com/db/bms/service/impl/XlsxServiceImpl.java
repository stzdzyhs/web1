package com.db.bms.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.db.bms.service.XlsxService;

/**
 * <b>功能：</b>用于事物处理<br>
 */

@Service("xlsxService")
public class XlsxServiceImpl implements XlsxService {

	//private static Logger log = Logger.getLogger(ArticleServiceImpl.class);

	/**
	 * the dir to save xlsx
	 */
	@Value("${xlsx.dir}")
	private String xlsxDir;
	
	@Override
	public String getFilePath(String filename) {
		String ret= this.xlsxDir +"/"+filename;
		return ret;
	}

}
