package com.ec.api.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.api.dao.PromotionInfoDao;
import com.ec.api.domain.PromotionInfo;
import com.ec.api.domain.query.PromotionInfoQuery;

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
	public int updatePromotionInfoStock(PromotionInfo promotionInfo) {
		return (Integer)update("PromotionInfo.updatePromotionInfoStock", promotionInfo);
	}

    @Override
    public int countPromotionProductByCondition(PromotionInfoQuery promotionInfoQuery) {
        return (Integer)queryForObject("PromotionInfo.countPromotionProductByCondition",promotionInfoQuery);
    }

    @Override
    public List<PromotionInfo> selectPromotionProductByConditionForPage(PromotionInfoQuery promotionInfoQuery) {
        return (List<PromotionInfo>)queryForList("PromotionInfo.selectPromotionProductByConditionForPage",promotionInfoQuery);
    }
}
