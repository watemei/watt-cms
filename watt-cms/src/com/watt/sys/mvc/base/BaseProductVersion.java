package com.watt.sys.mvc.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseProductVersion<M extends BaseProductVersion<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setVarName(java.lang.String varName) {
		set("var_name", varName);
	}

	public java.lang.String getVarName() {
		return get("var_name");
	}

	public void setVerCode(java.lang.String verCode) {
		set("ver_code", verCode);
	}

	public java.lang.String getVerCode() {
		return get("ver_code");
	}

	public void setFilePath(java.lang.String filePath) {
		set("file_path", filePath);
	}

	public java.lang.String getFilePath() {
		return get("file_path");
	}

	public void setType(java.lang.String type) {
		set("type", type);
	}

	public java.lang.String getType() {
		return get("type");
	}

	public void setWebsite(java.lang.String website) {
		set("website", website);
	}

	public java.lang.String getWebsite() {
		return get("website");
	}

	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}

	public java.lang.String getRemark() {
		return get("remark");
	}

	public void setCreator(java.lang.String creator) {
		set("creator", creator);
	}

	public java.lang.String getCreator() {
		return get("creator");
	}

	public void setUpdator(java.lang.String updator) {
		set("updator", updator);
	}

	public java.lang.String getUpdator() {
		return get("updator");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}

	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

}