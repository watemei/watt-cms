package com.watt.sys.mvc.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseActRuEventSubscr<M extends BaseActRuEventSubscr<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("ID_", id);
	}

	public java.lang.String getId() {
		return get("ID_");
	}

	public void setRev(java.lang.Integer rev) {
		set("REV_", rev);
	}

	public java.lang.Integer getRev() {
		return get("REV_");
	}

	public void setEventType(java.lang.String eventType) {
		set("EVENT_TYPE_", eventType);
	}

	public java.lang.String getEventType() {
		return get("EVENT_TYPE_");
	}

	public void setEventName(java.lang.String eventName) {
		set("EVENT_NAME_", eventName);
	}

	public java.lang.String getEventName() {
		return get("EVENT_NAME_");
	}

	public void setExecutionId(java.lang.String executionId) {
		set("EXECUTION_ID_", executionId);
	}

	public java.lang.String getExecutionId() {
		return get("EXECUTION_ID_");
	}

	public void setProcInstId(java.lang.String procInstId) {
		set("PROC_INST_ID_", procInstId);
	}

	public java.lang.String getProcInstId() {
		return get("PROC_INST_ID_");
	}

	public void setActivityId(java.lang.String activityId) {
		set("ACTIVITY_ID_", activityId);
	}

	public java.lang.String getActivityId() {
		return get("ACTIVITY_ID_");
	}

	public void setConfiguration(java.lang.String configuration) {
		set("CONFIGURATION_", configuration);
	}

	public java.lang.String getConfiguration() {
		return get("CONFIGURATION_");
	}

	public void setCreated(java.util.Date created) {
		set("CREATED_", created);
	}

	public java.util.Date getCreated() {
		return get("CREATED_");
	}

	public void setProcDefId(java.lang.String procDefId) {
		set("PROC_DEF_ID_", procDefId);
	}

	public java.lang.String getProcDefId() {
		return get("PROC_DEF_ID_");
	}

	public void setTenantId(java.lang.String tenantId) {
		set("TENANT_ID_", tenantId);
	}

	public java.lang.String getTenantId() {
		return get("TENANT_ID_");
	}

}