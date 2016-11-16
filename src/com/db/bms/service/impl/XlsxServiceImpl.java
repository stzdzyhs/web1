package com.db.bms.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.db.bms.entity.HouseDb;
import com.db.bms.entity.People;
import com.db.bms.service.XlsxService;


interface ISearch {
	public boolean match(People m);
}

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
	public String getFilePath(String filename) throws Exception {
		String ret= this.xlsxDir +"/"+filename;
		return ret;
	}
	
	
	Map<String, WeakReference<HouseDb> > houseDbCache = new HashMap<String, WeakReference<HouseDb> >();
	

	// the field index in xlsx
	public static final int IDX_SKYLINE_ID=0;
	public static final int IDX_ID=1;
	public static final int IDX_AID=2;
	public static final int IDX_NAME=3;
	public static final int IDX_SEX=4;
	public static final int IDX_BIRTHDAY=5;
	public static final int IDX_CID=6;
	public static final int IDX_ADDRESS=7;
	public static final int IDX_HOUSE_ADDRESS=8;
	public static final int IDX_TEL=9;
	public static final int IDX_IN_DATE=10;
	public static final int IDX_COMMENT=11;
	

	public static String getCellStrVal(Row row, int idx) {
		Cell c = row.getCell(idx);
		if(c==null) return null;
		
		String ret;
		// the problem is, tel is saved as numberic field, double, e.g: 8.6174393E7 ....
		if(c.getCellType()==Cell.CELL_TYPE_NUMERIC) {
			Double d = c.getNumericCellValue();
			ret = Long.toString(d.longValue());
			/*
			ret = Double.toString(c.getNumericCellValue());
			if(ret.endsWith(".0")) {
				ret = ret.substring(0,ret.length()-2);
			}
			*/
		}
		else {
			ret = c.getStringCellValue();
		}
		return ret;
	}
	
	
	static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date getCellDateVal(Row row, int idx) throws Exception {
		Cell c = row.getCell(idx);
		if(c==null) return null;
		Date d = null;
		if(c.getCellType()==Cell.CELL_TYPE_NUMERIC) {
			d = HSSFDateUtil.getJavaDate(c.getNumericCellValue());
		}
		else {
			String s = c.getStringCellValue();
			if(StringUtils.isEmpty(s)) {
				return null;
			}
			d = df.parse(s);
		}
		return d;
	}
	
	
	public static void setCellDateVal(Row row, int idx, Date d) throws Exception {
		Cell c = row.createCell(idx);
		if(d==null) {
			c.setCellValue("");
		}
		else {
			c.setCellValue(df.format(d));
		}
	}
	
	public static void writePeopleToRow(Row row, People p)  throws Exception {
		Cell c;
		
		c = row.createCell(IDX_SKYLINE_ID);c.setCellValue(p.skylineId);		
		c = row.createCell(IDX_ID); c.setCellValue(p.id);
		c = row.createCell(IDX_AID); c.setCellValue(p.aid);
		c = row.createCell(IDX_NAME); c.setCellValue(p.name);
		c = row.createCell(IDX_SEX); c.setCellValue(p.sex);
		c = row.createCell(IDX_BIRTHDAY); setCellDateVal(row, IDX_BIRTHDAY, p.birthday);
		c = row.createCell(IDX_CID); c.setCellValue(p.cid);
		c = row.createCell(IDX_ADDRESS); c.setCellValue(p.address);
		c = row.createCell(IDX_HOUSE_ADDRESS); c.setCellValue(p.houseAddress);
		c = row.createCell(IDX_TEL); c.setCellValue(p.tel);
		c = row.createCell(IDX_IN_DATE); setCellDateVal(row, IDX_IN_DATE, p.inDate);
		c = row.createCell(IDX_COMMENT); c.setCellValue(p.comment);
	}
	
	public People readPeopleFromRow(Row row) throws Exception {
		String v = getCellStrVal(row, IDX_ADDRESS);
		if(StringUtils.isEmpty(v) || "住址".equals(v)|| "地址".equals(v) ||"address".equals(v)) {
			return null;
		}
		
		People p = new People();
		p.skylineId = getCellStrVal(row, IDX_SKYLINE_ID);
		p.id = getCellStrVal(row, IDX_ID);
		p.aid = getCellStrVal(row, IDX_AID);
		p.name = getCellStrVal(row, IDX_NAME);
		p.sex = getCellStrVal(row, IDX_SEX);
		p.birthday = getCellDateVal(row, IDX_BIRTHDAY);
		p.cid = getCellStrVal(row, IDX_CID);
		p.address = getCellStrVal(row, IDX_ADDRESS);
		p.houseAddress = getCellStrVal(row, IDX_HOUSE_ADDRESS);
		if("黄毅昇".equals(p.name)) {
			System.out.println("******************** " + p + " " + p.birthday);
		}		
		p.tel = getCellStrVal(row, IDX_TEL);
		p.inDate = getCellDateVal(row, IDX_IN_DATE);
		p.comment = getCellStrVal(row, IDX_COMMENT);

		if(StringUtils.isEmpty(p.name)) {
			return null;
		}
	
		return p;
	}
	
	public HouseDb loadHouseDb(String filename) throws Exception {
		HouseDb ret = null;
        FileInputStream fis = null;
        try {
    		WeakReference<HouseDb> data = houseDbCache.get(filename);
    		if (data!=null) {
    			ret = data.get();
    			if(ret!=null) {
    				return ret;
    			}
    		}
    		
    		ret = new HouseDb();
        	fis = new FileInputStream(getFilePath(filename));
            Workbook wb = WorkbookFactory.create(fis);
            int n = wb.getNumberOfSheets();
            
            int lastRowNum;
            // 	and then iterate through them.
            for(int i = 0; i < n; i++) {
            	Sheet sheet = wb.getSheetAt(i);
            	if("死亡人口".equals(sheet.getSheetName()) || "迁出".equals(sheet.getSheetName()) ||
            			"样表".equals(sheet.getSheetName())) {
            		continue;
            	}
            	
                // Get a reference to a sheet and check to see if it contains any rows.
                if(sheet.getPhysicalNumberOfRows() > 0) {
                    lastRowNum = sheet.getLastRowNum();
                    for(int j = 0; j <= lastRowNum; j++) {
                    	Row row = sheet.getRow(j);
                            	
                    	if(sheet.getSheetName().equals("怡心苑") && j==122) {
                    		System.out.println("122:" + row.getCell(7).getStringCellValue() + ".");
                    	}
                            
                    	People p = readPeopleFromRow(row);
                    	if(p!=null) {
                    		ret.addPeople(p);
                    	}
                    }
                }
            }

            // add in cache
            data = new WeakReference<HouseDb>(ret);
            houseDbCache.put(filename,  data);
        }
        catch(Exception e) {
        	throw new IOException("process xlsx file error:" + filename, e);
        }
        finally {
        	if(fis!=null) fis.close();
        }
        
        return ret;
	}
	
	/**
	 * write search result to workbook
	 * @param result
	 * @throws Exception
	 */
	public Workbook writeResultToXlsx(List<People> result) throws Exception {
		Workbook wb = new XSSFWorkbook();
		Sheet sh = wb.createSheet("结果");
		int i = 0;
		for(People p:result) {
			Row r = sh.createRow(i);
			writePeopleToRow(r, p);
			i++;
		}
		return wb;
	}
	
	/**
	 * search house db
	 * @param houseDb
	 * @param wholeFamily - the whole family matched or not
	 * @param searchCriteria - search criteria callback
	 * @return - search result
	 * @throws Exception
	 */
	public List<People> searchHouseDb(HouseDb houseDb, boolean wholeFamily, ISearch searchCriteria) throws Exception {
		List<People> ret = new ArrayList<People>();

		for(String k:houseDb.addressOrder) {
			List<People> members = houseDb.get(k);
			
			for(int j=0;j<members.size();j++) {
				People m = members.get(j);			
				if (searchCriteria.match(m)) {
					if(wholeFamily) {
						for(j=0;j<members.size();j++) {
							ret.add(members.get(j));
						}
						break;
					}
					else {
						ret.add(m);
					}
				}
			}
		}
		return ret;
	}
	
	@Override
	public List<People> searchArmy(String filename) throws Exception {
		HouseDb houseDb = loadHouseDb(filename);
		ISearch s1 = new ISearch() {
			@Override
			public boolean match(People m) {
				Integer age = m.getAge();
				if( age!=null && age>=18 && age<=25 && "男".equals(m.sex) ) {
					return true;
				}
				return false;
			}
		};
		List<People> result = searchHouseDb(houseDb, false, s1);
		return result;
	}
	
	@Override
	public List<People> searchChildbearingWoman(String filename) throws Exception {
		HouseDb houseDb = loadHouseDb(filename);
		ISearch s1 = new ISearch() {
			@Override
			public boolean match(People m) {
				Integer age = m.getAge();
				if(age!=null && age>=22 && age<=30 && "女".equals(m.sex)) {
					return true;
				}
				return false;
			}
		};
		List<People> result = searchHouseDb(houseDb, false, s1);
		return result;
	}
	
	@Override
	public List<People> searchRetired(String filename) throws Exception {
		HouseDb houseDb = loadHouseDb(filename);
		ISearch s1 = new ISearch() {
			@Override
			public boolean match(People m) {
				Integer age = m.getAge();
				if(age!=null && age>=60) {
					return true;
				}
				return false;
			}
		};
		List<People> result = searchHouseDb(houseDb, false, s1);
		return result;
	}
	
	@Override
	public List<People> searchSpecialGroup(String filename) throws Exception {
		HouseDb houseDb = loadHouseDb(filename);
		ISearch s1 = new ISearch() {
			@Override
			public boolean match(People m) {
				return false;
			}
		};
		List<People> result = searchHouseDb(houseDb, false, s1);
		return result;
	}

	@Override
	public List<People> searchByKeyword(String filename, String keyword) throws Exception {
		if(StringUtils.isEmpty(keyword)) {
			return new ArrayList<People>();
		}
		
		HouseDb houseDb = loadHouseDb(filename);
		
		final String kw0 = keyword;
		// replace whiteSpace --> *
		keyword = keyword.replaceAll(" ",  "*");
		// replace *... --> .*
		keyword = keyword.replaceAll("\\*+", ".*");
		final Pattern re = Pattern.compile(keyword );
		
		ISearch s1 = new ISearch() {
			@Override
			public boolean match(People m) {
				if("黄毅昇".equals(m.name)) {
					System.out.println("黄毅昇");
				}
				
				if(re.matcher(m.address).find()) {
					return true;
				}
				else if(!StringUtils.isEmpty(m.houseAddress) && re.matcher(m.houseAddress).find()) {
					return true;
				}
				else if(re.matcher(m.name).matches()) {
					return true;
				}
				else if(kw0.length()>7 && !StringUtils.isEmpty(m.cid) && re.matcher(m.cid).find()) {// not test cid if keyword too short
					return true;
				}
				else if(!StringUtils.isEmpty(m.aid) && re.matcher(m.aid).find()) {
					return true;
				}
				else if(!StringUtils.isEmpty(m.tel) && re.matcher(m.tel).find()) {
					return true;
				}
				return false;
			}
		};

		List<People> result = searchHouseDb(houseDb, true, s1);
		return result;
	}

	
	// getter and setter
	//-------------------------------------------------------------------------
	public String getXlsxDir() {
		return xlsxDir;
	}


	public void setXlsxDir(String xlsxDir) {
		this.xlsxDir = xlsxDir;
	}
}
