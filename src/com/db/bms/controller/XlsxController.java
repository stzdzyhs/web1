package com.db.bms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.bms.service.XlsxService;

@RequestMapping("")
@Controller
public class XlsxController {

	//private final static Logger logger = Logger.getLogger(XlsxController.class);
	
	@Autowired
	XlsxService xlsxService;
	
    /**
     * get article image/resource
     * @param request
     * @param search
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/xlsx/{filename:.*}") 
    public void getArticleImage(HttpServletRequest request, HttpServletResponse response, @PathVariable("filename") String filename) throws Exception {
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
}
