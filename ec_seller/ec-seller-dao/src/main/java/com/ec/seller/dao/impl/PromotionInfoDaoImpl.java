package com.ec.seller.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.seller.dao.PromotionInfoDao;
import com.ec.seller.domain.PromotionInfo;
import com.ec.seller.domain.query.PromotionInfoQuery;

public class PromotionInfoDaoImpl extends SqlMapClientTemplate implements PromotionInfoDao {

	@Override
	public Integer insert(PromotionInfo promotionInfo) {
		return (Integer)insert("PromotionInfo.insert",promotionInfo);
	}

	@Override
	public void modify(PromotionInfo promotionInfo) {
		update("PromotionInfo.updateByPrimaryKey",promotionInfo);
	}

	@Override
	public PromotionInfo selectByPromotionId(int promotionId) {
		return (PromotionInfo)queryForObject("PromotionInfo.selectByPrimaryKey",promotionId);
	}

	@Override
	public int countByCondition(PromotionInfoQuery promotionInfoQuery) {
		return (Integer)queryForObject("PromotionInfo.countByCondition",promotionInfoQuery);
	}

	@Override
	public List<PromotionInfo> selectByCondition(
			PromotionInfoQuery promotionInfoQuery) {
		return (List<PromotionInfo>)queryForList("PromotionInfo.selectByCondition",promotionInfoQuery);
	}

	@Override
	public List<PromotionInfo> selectByConditionForPage(
			PromotionInfoQuery promotionInfoQuery) {
		return (List<PromotionInfo>)queryForList("PromotionInfo.selectByConditionForPage",promotionInfoQuery);
	}
	@Override
	public int queryPromotionCount(PromotionInfo promotionInfo) {
		return ( Integer )super.queryForObject("PromotionInfo.queryPromotionCount", promotionInfo);
	}

	@Override
	public List<PromotionInfo> queryPromotionList(PromotionInfo promotionInfo) {
		
		return super.queryForList("PromotionInfo.queryPromotionList", promotionInfo);
	}

	@Override
	public List<PromotionInfo> querystartPromotionList(Integer offsetStartProm) {
		
		return super.queryForList("PromotionInfo.querystartPromotionList", offsetStartProm);
	}

	@Override
	public List<PromotionInfo> querystopPromotionList(Integer offsetStopProm) {
		
		return super.queryForList("PromotionInfo.querystopPromotionList", offsetStopProm);
	}

	@Override
	public void startPromotion(PromotionInfo promotionInfo) {
		update("PromotionInfo.startPromotion",promotionInfo);
		
	}

	@Override
	public void stopPromotion(PromotionInfo promotionInfo) {
		update("PromotionInfo.stopPromotion",promotionInfo);
		
	}
}
