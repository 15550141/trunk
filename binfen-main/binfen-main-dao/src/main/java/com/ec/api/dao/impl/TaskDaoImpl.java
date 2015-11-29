package com.ec.api.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;

import com.ec.api.dao.TaskDao;
import com.ec.api.domain.Task;

public class TaskDaoImpl extends SqlMapClientTemplate implements TaskDao{
	@Override
	public Integer insert(Task task) {
		return (Integer) insert("Task.insert", task);
	}

	@Override
	public void modify(Task task) {
		update("Task.modify", task);
	}

	@Override
	public Task selectById(Integer id) {
		return (Task) queryForObject("Task.selectByPrimaryKey", id);
	}

	@Override
	public List<Task> selectByCondition(Task task) {
		return (List<Task>)queryForList("Task.selectByCondition", task);
	}
}