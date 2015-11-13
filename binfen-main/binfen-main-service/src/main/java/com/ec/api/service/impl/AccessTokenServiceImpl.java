package com.ec.api.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ec.api.common.utils.BFConstants;
import com.ec.api.common.utils.HttpUtils;
import com.ec.api.common.utils.JsonUtils;
import com.ec.api.dao.AccessTokenDao;
import com.ec.api.dao.UserInfoDao;
import com.ec.api.domain.AccessToken;
import com.ec.api.domain.OrderDetail;
import com.ec.api.domain.UserInfo;
import com.ec.api.service.AccessTokenService;
import com.ec.api.service.UserInfoService;
import com.ec.api.service.utils.EcUtils;

@Service(value="accessTokenService")
public class AccessTokenServiceImpl implements AccessTokenService {
	private static final Logger log = LoggerFactory.getLogger(AccessTokenServiceImpl.class);
	
	private AccessTokenDao accessTokenDao;
	
	private UserInfoDao userInfoDao;
	
	private DataSourceTransactionManager transactionManager;
	
	@Override
	public UserInfo login(String code, String ip) {
		
		try{
			StringBuilder tokenUrl = new StringBuilder();

			tokenUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
			tokenUrl.append(BFConstants.appId);
			tokenUrl.append("&secret=");
			tokenUrl.append(BFConstants.appSecret);
			tokenUrl.append("&code=");
			tokenUrl.append(code);
			tokenUrl.append("&grant_type=authorization_code");

			String tokenJson = HttpUtils.httpPostData(tokenUrl.toString(), "", "");
			log.error("访问url："+tokenUrl.toString()+ "		返回内容:"+tokenJson);
			if (StringUtils.isBlank(tokenJson)) {
				return null;
			}

			final AccessToken accessToken = JsonUtils.readValue(tokenJson, AccessToken.class);
			if(accessToken == null || StringUtils.isBlank(accessToken.getAccess_token()) || accessToken.getOpenid() == null){
				log.error("微信注册出错："+tokenJson);
				return null;
			}
			AccessToken query = new AccessToken();
//			query.setUnionid(accessToken.getUnionid());
			query.setOpenid(accessToken.getOpenid());
			query.setAppid(BFConstants.appId);
			List<AccessToken> list = this.accessTokenDao.selectByCondition(query);
			if(list == null || list.size() == 0){
				final UserInfo userinfo = new UserInfo();
				
				StringBuilder userUrl = new StringBuilder();
				userUrl.append("https://api.weixin.qq.com/sns/userinfo?access_token=");
				userUrl.append(accessToken.getAccess_token());
				userUrl.append("&openid=");
				userUrl.append(accessToken.getOpenid());
				userUrl.append("&lang=zh_CN");
				
				String userInfoJson = HttpUtils.httpPostData(userUrl.toString(), null, null);
				log.error("访问url："+userUrl.toString()+"		返回内容："+userInfoJson);
				if(StringUtils.isBlank(userInfoJson)){
					return null;
				}
				Map<String, Object> userInfoMap = JsonUtils.readValue(userInfoJson);
				String nickname = "";
				if(userInfoMap.get("nickname") != null){
					nickname = this.removeFourChar(userInfoMap.get("nickname").toString());
				}
				String sex = "0";
				if(userInfoMap.get("sex") != null){
					sex = userInfoMap.get("sex").toString();;
				}
				String headimgurl = "";
				if(userInfoMap.get("headimgurl") != null){
					headimgurl = userInfoMap.get("headimgurl").toString();
				}
				
				userinfo.setRegisterIp(ip);
				userinfo.setHeadimgurl(headimgurl);
				userinfo.setNickname(nickname);
				userinfo.setRegist_source(1);//1代表微信
				userinfo.setSex(Integer.parseInt(sex));
				userinfo.setUserType(3);//买家
				
				new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
					@Override
					protected void doInTransactionWithoutResult(TransactionStatus arg0) {
						Integer uid = userInfoDao.insert(userinfo);
						accessToken.setUid(uid);
						accessToken.setAppid(BFConstants.appId);
						accessTokenDao.insert(accessToken);
					}
				});			
				log.error("注册用户成功！uid："+userinfo.getUserId());
				return userinfo;
			}
			
			AccessToken dbAccessToken = list.get(0);
			//根据dbAccesssToken 获取用户uid，根据uid获取用户信息，返回用户信息
			UserInfo userinfo = userInfoDao.selectByUserId(dbAccessToken.getUid());
			log.error("正常登陆成功！uid："+userinfo.getUserId());
			
			return userinfo;
		}catch (Exception e) {
			log.error("", e);
		}

		return null;
	}
	
	/**
     * 替换四个字节的字符 ‘\xF0\x9F\x98\x84\xF0\x9F）的解决方案
     * @author ChenGuiYong
     * @data 2015年8月11日 上午10:31:50
     * @param content
     * @return
     */
    public String removeFourChar(String content) {
        byte[] conbyte = content.getBytes();
        for (int i = 0; i < conbyte.length; i++) {
            if ((conbyte[i] & 0xF8) == 0xF0) {
                for (int j = 0; j < 4; j++) {                          
                    conbyte[i+j]=0x30;                     
                }  
                i += 3;
            }
        }
        content = new String(conbyte);
        return content.replaceAll("0000", "");
    }

	public void setAccessTokenDao(AccessTokenDao accessTokenDao) {
		this.accessTokenDao = accessTokenDao;
	}


	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	
}