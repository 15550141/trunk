package com.ec.erp.manager.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Repository;
import com.ec.erp.common.utils.PaginatedList;
import com.ec.erp.common.utils.impl.PaginatedArrayList;
import com.ec.erp.dao.PromotionProductDao;
import com.ec.erp.domain.PromotionProduct;
import com.ec.erp.manager.PromotionProductManager;
import com.ec.erp.domain.query.PromotionProductQuery;

@Repository
public class PromotionProductManagerImpl implements PromotionProductManager{
	
	@Autowired
	private PromotionProductDao promotionProductDao;
	private final static Log LOG = LogFactory.getLog(PromotionProductManagerImpl.class);

	@Override
	public PaginatedList<PromotionProduct> queryPromotionList(
			PromotionProduct promotionProduct) {
		PaginatedList<PromotionProduct> promotionProductList =null;
		try {		
			int count = promotionProductDao.queryPromotionCount(promotionProduct);
			//创建一个分页的促销对象
			promotionProductList=new PaginatedArrayList<PromotionProduct>(promotionProduct
					.getPageIndex(),promotionProduct.getPageSize());
			promotionProductList.setTotalItem(count);//设总条目数
			int startRow=promotionProductList.getStartRow();
			if (startRow == 0) {
				startRow = 1;
			}
			promotionProduct.setStartRow(startRow-1);
			List<PromotionProduct> list= promotionProductDao.queryPromotionList(promotionProduct);
			promotionProductList.addAll(list);
			
		}
		catch (BadSqlGrammarException e1) {
			LOG.error("-----queryPromotionList BadSqlGrammarException-----" + e1.getSql() + "end", e1);
		}
		catch (Exception e) {
			LOG.error("-----PromotionProductManagerImpl.queryPromotionList-----", e);
			// TODO: handle exception
		}
		 
		return promotionProductList;
	}

	@Override
	public PaginatedList<PromotionProduct> queryPromotionPList(
			PromotionProduct promotionProduct) {
		PaginatedList<PromotionProduct> promotionProductList =null;
		try {		
			int count = promotionProductDao.queryPromotionCount(promotionProduct);
			//创建一个分页的促销对象
			promotionProductList=new PaginatedArrayList<PromotionProduct>(promotionProduct
					.getPageIndex(),promotionProduct.getPageSize());
			promotionProductList.setTotalItem(count);//设总条目数
			int startRow=promotionProductList.getStartRow();
			if (startRow == 0) {
				startRow = 1;
			}
			promotionProduct.setStartRow(startRow-1);
			List<PromotionProduct> list= promotionProductDao.queryPromotionPList(promotionProduct);
			promotionProductList.addAll(list);
			
		}
		catch (BadSqlGrammarException e1) {
			LOG.error("-----queryPromotionList BadSqlGrammarException-----" + e1.getSql() + "end", e1);
		}
		catch (Exception e) {
			LOG.error("-----PromotionProductManagerImpl.queryPromotionList-----", e);
			// TODO: handle exception
		}
		 
		return promotionProductList;
	}
	
	@Override
	public Integer insert(PromotionProduct promotionProduct) {
		
		return promotionProductDao.insert(promotionProduct);
	}

	@Override
	public void modify(PromotionProduct promotionProduct) {
		promotionProductDao.modify(promotionProduct);
		
	}

	@Override
	public void modifyByPromtionId(PromotionProduct promotionProduct) {
		promotionProductDao.modifyByPromtionId(promotionProduct);
		
	}

	@Override
	public List<PromotionProduct> selectByCondition(
			com.ec.erp.domain.query.PromotionProductQuery promotionProductQuery) {
		
		return promotionProductDao.selectByCondition(promotionProductQuery);
	}
	



	
	


}
