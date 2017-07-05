package com.watt.sys.mvc.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseActIdMembership<M extends BaseActIdMembership<M>> extends Model<M> implements IBean {

	public void setUserId(java.lang.String userId) {
		set("USER_ID_", userId);
	}

	public java.lang.String getUserId() {
		return get("USER_ID_");
	}

	public void setGroupId(java.lang.String groupId) {
		set("GROUP_ID_", groupId);
	}

	public java.lang.String getGroupId() {
		return get("GROUP_ID_");
	}

}