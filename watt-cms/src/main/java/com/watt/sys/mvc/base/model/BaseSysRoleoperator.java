package com.watt.sys.mvc.base.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSysRoleoperator<M extends BaseSysRoleoperator<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setRoleId(java.lang.String roleId) {
		set("role_id", roleId);
	}

	public java.lang.String getRoleId() {
		return get("role_id");
	}

	public void setOperatorId(java.lang.String operatorId) {
		set("operator_id", operatorId);
	}

	public java.lang.String getOperatorId() {
		return get("operator_id");
	}

}