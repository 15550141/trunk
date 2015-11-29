package com.ec.api.dao;

import java.util.List;

import com.ec.api.domain.Task;

public interface TaskDao{
	
	public Integer insert(Task task);

	public void modify(Task task);

	public Task selectById(Integer id);

	public List<Task> selectByCondition(Task task);
}