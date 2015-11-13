package com.ec.seller.dao.impl;

import com.ec.seller.dao.VenderInfoDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ec.seller.domain.BusinessUserExt;
import com.ec.seller.domain.BusinessUserExt;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class VenderInfoDAOImpl extends SqlMapClientTemplate implements VenderInfoDAO {

	@Override
	public void addVender(BusinessUserExt venderInfo) {
		super.insert("vender_info.addVender", venderInfo);
		
	}

	
}