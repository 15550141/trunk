package com.ec.seller.manager.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Repository;
import com.ec.seller.common.utils.PaginatedList;
import com.ec.seller.common.utils.impl.PaginatedArrayList;
import com.ec.seller.dao.PromotionInfoDao;
import com.ec.seller.domain.PromotionInfo;
import com.ec.seller.manager.PromotionInfoManager;

@Repository
public class PromotionInfoManagerImpl implements PromotionInfoManager{
	
	@Autowired
	private PromotionInfoDao promotionInfoDao;
	private final static Log LOG = LogFactory.getLog(PromotionInfoManagerImpl.class);

	@Override
	public PaginatedList<PromotionInfo> queryPromotionList(
			PromotionInfo promotionInfo) {
		PaginatedList<PromotionInfo> promotionInfoList =null;
		try {		
			int count = promotionInfoDao.queryPromotionCount(promotionInfo);
			//创建一个分页的促销对象
			promotionInfoList=new PaginatedArrayList<PromotionInfo>(promotionInfo
					.getPageIndex(),promotionInfo.getPageSize());
			promotionInfoList.setTotalItem(count);//设总条目数
			int startRow=promotionInfoList.getStartRow();
			if (startRow == 0) {
				startRow = 1;
			}
			promotionInfo.setStartRow(startRow-1);
			List<PromotionInfo> list= promotionInfoDao.queryPromotionList(promotionInfo);
			promotionInfoList.addAll(list);
			
		}
		catch (BadSqlGrammarException e1) {
			LOG.error("-----queryPromotionList BadSqlGrammarException-----" + e1.getSql() + "end", e1);
		}
		catch (Exception e) {
			LOG.error("-----PromotionInfoManagerImpl.queryPromotionList-----", e);
			// TODO: handle exception
		}
		 
		return promotionInfoList;
	}

	@Override
	public Integer insert(PromotionInfo promotionInfo) {
		
		return promotionInfoDao.insert(promotionInfo);
	}

	@Override
	public void modify(PromotionInfo promotionInfo) {
		promotionInfoDao.modify(promotionInfo);
		
	}

	@Override
	public PromotionInfo selectByPromotionId(int promotionId) {
		
		return promotionInfoDao.selectByPromotionId(promotionId);
	}


	
	


}
