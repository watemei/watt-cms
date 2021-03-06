package com.watt.sys.mvc.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseActRuJob<M extends BaseActRuJob<M>> extends Model<M> implements IBean {

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

	public void setType(java.lang.String type) {
		set("TYPE_", type);
	}

	public java.lang.String getType() {
		return get("TYPE_");
	}

	public void setLockExpTime(java.util.Date lockExpTime) {
		set("LOCK_EXP_TIME_", lockExpTime);
	}

	public java.util.Date getLockExpTime() {
		return get("LOCK_EXP_TIME_");
	}

	public void setLockOwner(java.lang.String lockOwner) {
		set("LOCK_OWNER_", lockOwner);
	}

	public java.lang.String getLockOwner() {
		return get("LOCK_OWNER_");
	}

	public void setExclusive(java.lang.Boolean exclusive) {
		set("EXCLUSIVE_", exclusive);
	}

	public java.lang.Boolean getExclusive() {
		return get("EXCLUSIVE_");
	}

	public void setExecutionId(java.lang.String executionId) {
		set("EXECUTION_ID_", executionId);
	}

	public java.lang.String getExecutionId() {
		return get("EXECUTION_ID_");
	}

	public void setProcessInstanceId(java.lang.String processInstanceId) {
		set("PROCESS_INSTANCE_ID_", processInstanceId);
	}

	public java.lang.String getProcessInstanceId() {
		return get("PROCESS_INSTANCE_ID_");
	}

	public void setProcDefId(java.lang.String procDefId) {
		set("PROC_DEF_ID_", procDefId);
	}

	public java.lang.String getProcDefId() {
		return get("PROC_DEF_ID_");
	}

	public void setRetries(java.lang.Integer retries) {
		set("RETRIES_", retries);
	}

	public java.lang.Integer getRetries() {
		return get("RETRIES_");
	}

	public void setExceptionStackId(java.lang.String exceptionStackId) {
		set("EXCEPTION_STACK_ID_", exceptionStackId);
	}

	public java.lang.String getExceptionStackId() {
		return get("EXCEPTION_STACK_ID_");
	}

	public void setExceptionMsg(java.lang.String exceptionMsg) {
		set("EXCEPTION_MSG_", exceptionMsg);
	}

	public java.lang.String getExceptionMsg() {
		return get("EXCEPTION_MSG_");
	}

	public void setDuedate(java.util.Date duedate) {
		set("DUEDATE_", duedate);
	}

	public java.util.Date getDuedate() {
		return get("DUEDATE_");
	}

	public void setRepeat(java.lang.String repeat) {
		set("REPEAT_", repeat);
	}

	public java.lang.String getRepeat() {
		return get("REPEAT_");
	}

	public void setHandlerType(java.lang.String handlerType) {
		set("HANDLER_TYPE_", handlerType);
	}

	public java.lang.String getHandlerType() {
		return get("HANDLER_TYPE_");
	}

	public void setHandlerCfg(java.lang.String handlerCfg) {
		set("HANDLER_CFG_", handlerCfg);
	}

	public java.lang.String getHandlerCfg() {
		return get("HANDLER_CFG_");
	}

	public void setTenantId(java.lang.String tenantId) {
		set("TENANT_ID_", tenantId);
	}

	public java.lang.String getTenantId() {
		return get("TENANT_ID_");
	}

}
