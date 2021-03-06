package com.watt.sys.mvc.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseActHiProcinst<M extends BaseActHiProcinst<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("ID_", id);
	}

	public java.lang.String getId() {
		return get("ID_");
	}

	public void setProcInstId(java.lang.String procInstId) {
		set("PROC_INST_ID_", procInstId);
	}

	public java.lang.String getProcInstId() {
		return get("PROC_INST_ID_");
	}

	public void setBusinessKey(java.lang.String businessKey) {
		set("BUSINESS_KEY_", businessKey);
	}

	public java.lang.String getBusinessKey() {
		return get("BUSINESS_KEY_");
	}

	public void setProcDefId(java.lang.String procDefId) {
		set("PROC_DEF_ID_", procDefId);
	}

	public java.lang.String getProcDefId() {
		return get("PROC_DEF_ID_");
	}

	public void setStartTime(java.util.Date startTime) {
		set("START_TIME_", startTime);
	}

	public java.util.Date getStartTime() {
		return get("START_TIME_");
	}

	public void setEndTime(java.util.Date endTime) {
		set("END_TIME_", endTime);
	}

	public java.util.Date getEndTime() {
		return get("END_TIME_");
	}

	public void setDuration(java.lang.Long duration) {
		set("DURATION_", duration);
	}

	public java.lang.Long getDuration() {
		return get("DURATION_");
	}

	public void setStartUserId(java.lang.String startUserId) {
		set("START_USER_ID_", startUserId);
	}

	public java.lang.String getStartUserId() {
		return get("START_USER_ID_");
	}

	public void setStartActId(java.lang.String startActId) {
		set("START_ACT_ID_", startActId);
	}

	public java.lang.String getStartActId() {
		return get("START_ACT_ID_");
	}

	public void setEndActId(java.lang.String endActId) {
		set("END_ACT_ID_", endActId);
	}

	public java.lang.String getEndActId() {
		return get("END_ACT_ID_");
	}

	public void setSuperProcessInstanceId(java.lang.String superProcessInstanceId) {
		set("SUPER_PROCESS_INSTANCE_ID_", superProcessInstanceId);
	}

	public java.lang.String getSuperProcessInstanceId() {
		return get("SUPER_PROCESS_INSTANCE_ID_");
	}

	public void setDeleteReason(java.lang.String deleteReason) {
		set("DELETE_REASON_", deleteReason);
	}

	public java.lang.String getDeleteReason() {
		return get("DELETE_REASON_");
	}

	public void setTenantId(java.lang.String tenantId) {
		set("TENANT_ID_", tenantId);
	}

	public java.lang.String getTenantId() {
		return get("TENANT_ID_");
	}

	public void setName(java.lang.String name) {
		set("NAME_", name);
	}

	public java.lang.String getName() {
		return get("NAME_");
	}

}
