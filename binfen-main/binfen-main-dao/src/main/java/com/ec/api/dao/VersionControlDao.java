package com.ec.api.dao;

import com.ec.api.domain.Address;
import com.ec.api.domain.query.AddressQuery;
import com.ec.api.domain.version.VersionControl;
import com.ec.api.domain.version.query.VersionControlQuery;

import java.util.List;

/**
 * 版本控制
 */
public interface VersionControlDao {

	/**
	 * 依据地址ID修改
	 * @param versionControl
	 */
	public void modify(VersionControl versionControl);

	/**
	 * 依据地址ID查询
	 * @param id
	 * @return
	 */
	public VersionControl selectById(Integer id);

	/**
	 * 根据相应的条件查询满足条件的地址信息的总数
	 * @param versionControlQuery
	 * @return
	 */
	public Integer countByCondition(VersionControlQuery versionControlQuery);

	/**
	 * 根据相应的条件查询地址信息
	 * @param versionControlQuery
	 * @return
	 */
	public List<VersionControl> selectByCondition(VersionControlQuery versionControlQuery);

    /**
	 * 根据相应的条件分页查询地址信息
	 * @param versionControlQuery
	 * @return
	 */
	public List<VersionControl> selectByConditionForPage(VersionControlQuery versionControlQuery);
}