package com.ec.erp.dao.impl;

import com.ec.erp.dao.VenderInfoDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ec.erp.domain.BusinessUserExt;
import com.ec.erp.domain.BusinessUserExt;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class VenderInfoDAOImpl extends SqlMapClientTemplate implements VenderInfoDAO {

	@Override
	public void addVender(BusinessUserExt venderInfo) {
		super.insert("vender_info.addVender", venderInfo);
		
	}

	
}