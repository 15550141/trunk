package com.ec.api.dao.impl;

import com.ec.api.dao.VersionControlDao;
import com.ec.api.domain.Address;
import com.ec.api.domain.version.VersionControl;
import com.ec.api.domain.version.query.VersionControlQuery;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import java.util.List;

/**
 * User: iboy
 * Date: 2014-9-28
 * Time: 19:30:37
 */
public class VersionControlDaoImpl extends SqlMapClientTemplate implements VersionControlDao {
    @Override
    public void modify(VersionControl versionControl) {
        update("VersionControl.updateByPrimaryKey", versionControl);//To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public VersionControl selectById(Integer id) {
        return (VersionControl) queryForObject("VersionControl.selectByPrimaryKey", id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Integer countByCondition(VersionControlQuery versionControlQuery) {
        return (Integer) queryForObject("VersionControl.countByCondition", versionControlQuery);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<VersionControl> selectByCondition(VersionControlQuery versionControlQuery) {
        return (List<VersionControl>)queryForList("VersionControl.selectByCondition",versionControlQuery);  //To change body of implemented methods use File | Settings | File Templates.
    }
    
     @Override
    public List<VersionControl> selectByConditionForPage(VersionControlQuery versionControlQuery) {
        return (List<VersionControl>)queryForList("VersionControl.selectByConditionForPage",versionControlQuery);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
