package com.ec.seller.dao;

import java.util.List;

import com.ec.seller.domain.Task;

public interface TaskDao{
	
	public Integer insert(Task task);

	public void modify(Task task);

	public Task selectById(Integer id);

	public List<Task> selectByCondition(Task task);
}