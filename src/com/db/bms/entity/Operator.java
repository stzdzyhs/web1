package com.db.bms.entity;

import java.util.ArrayList;
import java.util.List;

import com.db.bms.utils.StringUtils;

public class Operator extends BaseModel {

	public Long operatorNo;

	public String operatorId;

	public String operatorName;

	public String operatorPwd;

	// this is some duplicated since in mapper xml, can not access enum !
	public static final int TYPE_SUPER_ADMIN = 0;
	public static final int TYPE_COMPANY_ADMIN = 1;
	public static final int TYPE_ORDINARY_OPER = 2;
	public Integer type;

	public static final int STATUS_ENABLED=0;
	public static final int STATUS_DISABLED=1;
	public Integer status;

	public String operatorEmail;

	public String operatorTel;

	public String operatorDescribe;

	public Long totalSize;

	public Long usedSize;

	public String usedSizeFmt;

	public Long companyNo;

	public Long createBy;

	public String createTime;

	public Company company;

	public List<Role> roles;

	public Operator operator;

	public static Operator SA = new Operator(Operator.TYPE_COMPANY_ADMIN);

	public List<Company> companys;
	
    public Long topicId; 
	
	public Long albumNo;
 
	public Long columnNo;
	
	public Long articleNo;
	
	public ResourceAllocation resAloc;
	
	/** default constructor */
	public Operator() {
	}
     
	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Long getAlbumNo() {
		return albumNo;
	}

	public void setAlbumNo(Long albumNo) {
		this.albumNo = albumNo;
	}

	public Long getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(Long columnNo) {
		this.columnNo = columnNo;
	}

	public Long getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(Long articleNo) {
		this.articleNo = articleNo;
	} 
	
	public Operator(int type) {
		this.type = type;
	}

	public Operator(Long operatorNo) {
		this.operatorNo = operatorNo;
	}

	/** minimal constructor */
	public Operator(String operatorId, String operatorName, String operatorPwd) {
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.operatorPwd = operatorPwd;
	}

	/** full constructor */
	public Operator(String operatorId, String operatorName, String operatorPwd,
			String operatorEmail, String operatorTel, String operatorDescribe,
			Integer type) {
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.operatorPwd = operatorPwd;
		this.operatorEmail = operatorEmail;
		this.operatorTel = operatorTel;
		this.operatorDescribe = operatorDescribe;
		this.type = type;
	}

	// Property accessors
	public Long getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(Long operatorNo) {
		this.operatorNo = operatorNo;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorPwd() {
		return operatorPwd;
	}

	public void setOperatorPwd(String operatorPwd) {
		this.operatorPwd = operatorPwd;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOperatorEmail() {
		return operatorEmail;
	}

	public void setOperatorEmail(String operatorEmail) {
		this.operatorEmail = operatorEmail;
	}

	public String getOperatorTel() {
		return operatorTel;
	}

	public void setOperatorTel(String operatorTel) {
		this.operatorTel = operatorTel;
	}

	public String getOperatorDescribe() {
		return operatorDescribe;
	}

	public void setOperatorDescribe(String operatorDescribe) {
		this.operatorDescribe = operatorDescribe;
	}

	public Long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}

	public Long getUsedSize() {
		return usedSize;
	}

	public void setUsedSize(Long usedSize) {
		this.usedSize = usedSize;
		if (this.totalSize != -1) {
			this.usedSizeFmt = StringUtils.byteConversionGBMBKB(usedSize);
		}
	}

	public String getUsedSizeFmt() {
		return usedSizeFmt;
	}

	public void setUsedSizeFmt(String usedSizeFmt) {
		this.usedSizeFmt = usedSizeFmt;
	}

	public Long getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(Long companyNo) {
		this.companyNo = companyNo;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public List<Company> getCompanys() {
		return companys;
	}

	public void setCompanys(List<Company> companys) {
		this.companys = companys;
	}

	public ResourceAllocation getResAloc() {
		return resAloc;
	}

	public void setResAloc(ResourceAllocation resAloc) {
		this.resAloc = resAloc;
	}
	
	public List<AllocCommand> getAllocCommands(String cmdStr){
		if(cmdStr == null || cmdStr.isEmpty()){
			return null;
		}
		String[] cmdArr = cmdStr.split(",");
		List<AllocCommand> res = new ArrayList<AllocCommand>();
		AllocCommand cmd = null;
		for (int i = 0; i < cmdArr.length; i++) {
			cmd = new AllocCommand();
			cmd.setId(Long.parseLong(cmdArr[i], 16));
			cmd.setCmdName(CommandNameMap.cmdNameMap.get(cmd.getId()));
			res.add(cmd);
		}
		return res;
	}
	
	public String getCmdStr(){
		if(this.resAloc == null || this.resAloc.getCmdStr() == null || this.resAloc.getCmdStr().isEmpty()){
			return "";
		}
		else{
			StringBuffer buff = new StringBuffer();
			String[] cmdArr = this.resAloc.getCmdStr().split(",");
			for (int i = 0; i < cmdArr.length; i++) {
				if(cmdArr[i].equalsIgnoreCase("1")){
					buff.append("").append("允许关联相册");
				}
				else if(cmdArr[i].equalsIgnoreCase("2")){
					buff.append("  ").append("允许发布相册");
				}
				else if(cmdArr[i].equalsIgnoreCase("3")){
					buff.append("  ").append("允许新增图片");				
				}
				else if(cmdArr[i].equalsIgnoreCase("4")){
					buff.append("  ").append("允许审核图片");
				}
				else if(cmdArr[i].equalsIgnoreCase("5")){
					buff.append("  ").append("允许发布图片");
				}
				else if(cmdArr[i].equalsIgnoreCase("6")){
					buff.append("  ").append("允许新增子版块");
				}
				else if(cmdArr[i].equalsIgnoreCase("7")){
					buff.append("  ").append("允许审核子版块");
				}
				else if(cmdArr[i].equalsIgnoreCase("8")){
					buff.append("  ").append("允许发布子版块");
				}
				else if(cmdArr[i].equalsIgnoreCase("9")){
					buff.append("  ").append("允许新增文章");
				}
				else if(cmdArr[i].equalsIgnoreCase("10")){
					buff.append("  ").append("允许审核文章");
				}
				else if(cmdArr[i].equalsIgnoreCase("11")){
					buff.append("  ").append("允许发布文章");
				}
			}
			return buff.toString();
		}
	}

	public static enum OperatorType {
		SUPER_ADMIN(0), COMPANY_ADMIN(1), ORDINARY_OPER(2);

		private final int index;

		private OperatorType(int index) {
			this.index = index;
		}

		public int getIndex() {
			return this.index;
		}

		public static OperatorType getType(int index) {
			switch (index) {
			case 0:
				return SUPER_ADMIN;
			case 1:
				return COMPANY_ADMIN;
			case 2:
				return ORDINARY_OPER;
			}
			return ORDINARY_OPER;
		}
	}

	/**
	 * 是否为super_admin
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isSuperAdmin(int type) {
		if (type == OperatorType.SUPER_ADMIN.index) {
			return true;
		}
		return false;
	}

	/**
	 * is serup admin
	 * 
	 * @return
	 */
	public boolean isSuperAdmin() {
		if (this.type != null && this.type == Operator.TYPE_SUPER_ADMIN) {
			return true;
		}
		return false;
	}

	public static enum OperatorStatus {
		NORMAL(0), SUSPEND(1);

		private final int index;

		private OperatorStatus(int index) {
			this.index = index;
		}

		public int getIndex() {
			return this.index;
		}
      
		public static OperatorStatus getStatus(int index) {
			switch (index) {
			case 0:
				return NORMAL;
			case 1:
				return SUSPEND;
			}
			return NORMAL;
		}
	}

	public void setDefaultNull() {

	}

	public String toString() {
		return com.alibaba.fastjson.JSON.toJSONString(this);
	}
}