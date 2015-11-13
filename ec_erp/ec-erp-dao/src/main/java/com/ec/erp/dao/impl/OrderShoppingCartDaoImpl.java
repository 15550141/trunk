package com.ec.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ec.erp.dao.OrderShoppingCartDao;
import com.ec.erp.domain.OrderShoppingCart;

public class OrderShoppingCartDaoImpl extends SqlMapClientTemplate implements OrderShoppingCartDao {

	@Override
	public Integer insert(OrderShoppingCart orderShoppingCart) {
		return (Integer)insert("OrderShoppingCart.insert",orderShoppingCart);
	}

	@Override
	public List<OrderShoppingCart> selectByOrderId(int orderId) {
		return (List<OrderShoppingCart>)queryForList("OrderShoppingCart.selectByOrderId",orderId);
	}

}
