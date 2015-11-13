package com.ec.api.domain.version;

import java.util.Date;

/**
 * 版本控制
 * User: iboy
 * Date: 2014-9-28
 * Time: 18:19:27
 */
public class VersionControl {

    /** id */
    private Integer id;
    /** 版本名称 version_name */
    private String versionName;
    /** 版本号 version_no */
    private Integer versionNo;
    /** 版本URL version_url */
    private String versionUrl;
    /** 版本类型 version_type */
    private String versionType;
    /** 版本介绍 version_intro */
    private String versionIntro;
    /** 有效 */
    private Integer yn;
    /** 更新标识 update_flag */
    private Integer updateFlag;
    /** 创建时间 */
    private Date created;
     /** 修改时间 */
    private Date modified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
    }

    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType;
    }

    public String getVersionIntro() {
        return versionIntro;
    }

    public void setVersionIntro(String versionIntro) {
        this.versionIntro = versionIntro;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "VersionControl{" +
                "modified=" + modified +
                ", created=" + created +
                ", updateFlag=" + updateFlag +
                ", yn=" + yn +
                ", versionIntro='" + versionIntro + '\'' +
                ", versionType='" + versionType + '\'' +
                ", versionUrl='" + versionUrl + '\'' +
                ", versionNo='" + versionNo + '\'' +
                ", versionName='" + versionName + '\'' +
                ", id=" + id +
                '}';
    }
}
