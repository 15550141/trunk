package com.ec.erp.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ec.erp.manager.BusinessUserExtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.ec.erp.common.utils.PaginatedList;
import com.ec.erp.common.utils.impl.PaginatedArrayList;
import com.ec.erp.domain.Category;
import com.ec.erp.domain.Sku;
import com.ec.erp.domain.UserInfo;
import com.ec.erp.domain.query.ItemQuery;
import com.ec.erp.domain.query.SkuQuery;
import com.ec.erp.domain.query.UserInfoQuery;
import com.ec.erp.manager.UserManager;
import com.ec.erp.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserManager userManager;
    @Autowired
    private BusinessUserExtManager businessUserExtManager;

	@Override
	public UserInfo queryUser(String loginname, String loginpwd) {
		Map<String,String> params=new HashMap<String,String>();
		params.put("mobile", loginname);
		params.put("password", loginpwd);
		UserInfo userInfo = userManager.query(params);
		return userInfo;
	}

	@Override
	public Integer addUser(UserInfo user) {
		// TODO Auto-generated method stub
		return userManager.addUser(user);
		
	}

	@Override
	public void updateUser(UserInfo user) {
		
		userManager.updateUser(user);
		
	}

	@Override
	public Map<String, Object> queryUserList(UserInfoQuery userInfoQuery) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PaginatedList<UserInfoQuery> userList =null;
		//创建一个分页的促销对象
		userList=new PaginatedArrayList<UserInfoQuery>(userInfoQuery.getPageNo(),userInfoQuery.getPageSize());
		int count = userManager.countByCondition(userInfoQuery);
		userList.setTotalItem(count);
		int startRow=userList.getStartRow();
		if (startRow == 0) {
			startRow = 1;
		}
		userInfoQuery.setStart(startRow-1);
		List<UserInfoQuery> list= userManager.selectByConditionWithPage(userInfoQuery);
		if( null != list ){
            for( UserInfoQuery uiq : list ){
                uiq.setBusinessUserExt(businessUserExtManager.selectByUserId(uiq.getUserId()));
            }
        }
        userList.addAll(list);
		resultMap.put("userList", userList);
		resultMap.put("userInfoQuery", userInfoQuery);	
		return resultMap;
	}

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void setBusinessUserExtManager(BusinessUserExtManager businessUserExtManager) {
        this.businessUserExtManager = businessUserExtManager;
    }
}
