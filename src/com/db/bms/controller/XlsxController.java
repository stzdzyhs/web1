package com.db.bms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.db.bms.entity.People;
import com.db.bms.entity.Search;
import com.db.bms.service.XlsxService;


@RequestMapping("")
@Controller
public class XlsxController {

	private final static Logger logger = Logger.getLogger(XlsxController.class);
	
	@Autowired
	XlsxService xlsxService;
	
    /**
     * get xlsx
     * @param request
     * @param search
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/xlsx/{filename:.*}") 
    public void getXlsx(HttpServletRequest request, HttpServletResponse response, @PathVariable("filename") String filename) throws Exception {
    	String filePath = this.xlsxService.getFilePath(filename);
    	
    	ServletContext context = request.getSession().getServletContext();
    	String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
    	
        response.setContentType(mimeType);        
        
        File f = new File(filePath);
        if(!f.exists()) {
        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
        	return;
        }
        
    	InputStream is = new FileInputStream(f);
    	try {
    		IOUtils.copy(is, response.getOutputStream());
    	}
    	finally {
    		IOUtils.closeQuietly(is);
    	}
    	
    }
    
	@RequestMapping(value = "/exportSearch.action")
	public void downloadTemplate(HttpServletRequest request, HttpServletResponse response, String data) throws Exception {
        try {
        	Search search = JSON.parseObject(data, Search.class);
			if(search.searchType==null) {
				throw new Exception("invalid searchType: null");
			}
			
			List<People> result = null;
			String ft;
			
			if(search.searchType.equals(Search.SEARCH_TYPE_SEARCH)) {
				ft = "搜索";
				result = xlsxService.searchByKeyword(search.filename, search.keyword);
			}
			else if(search.searchType.equals(Search.SEARCH_TYPE_ARMY)) {
				ft = "征兵人员";
				result = xlsxService.searchArmy(search.filename);
			}
			else if(search.searchType.equals(Search.SEARCH_TYPE_CHILDBEARING_WOMAN)) {
				ft = "育龄妇女";
				result = xlsxService.searchChildbearingWoman(search.filename);
			}
			else if(search.searchType.equals(Search.SEARCH_TYPE_RETIRED)) {
				ft = "退休人员";
				result = xlsxService.searchRetired(search.filename);
			}
			else if(search.searchType.equals(Search.SEARCH_TYPE_SPECIAL_GROUP)) {
				ft = "特殊人群";
				result = xlsxService.searchSpecialGroup(search.filename);
			}
			else {
				throw new Exception("invalid searchType:" + search);
			}
			
			Workbook wb = xlsxService.writeResultToXlsx(result);
			
			String fileName = ft + (new SimpleDateFormat("yyyy_MM_dd_HH_mm")).format(new Date()) + ".xlsx";
			
			// to fix chn char missing.
			//fileName= new String(fileName.getBytes("utf-8"), "ISO_8859_1");
			
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition", "attachment;Filename="+fileName);
			response.setCharacterEncoding("UTF-8");		  
			
			OutputStream output = response.getOutputStream();
			wb.write(output);
			output.flush();
			output.close();
        }
        catch (Exception e) {
        	response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        	logger.error("exportSearch Error", e);
        }
	}

}
