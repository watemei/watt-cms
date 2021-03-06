package com.watt.sys.mvc.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSysDepartment<M extends BaseSysDepartment<M>> extends Model<M> implements IBean {

	public void setIds(java.lang.String ids) {
		set("ids", ids);
	}

	public java.lang.String getIds() {
		return get("ids");
	}

	public void setVersion(java.lang.Long version) {
		set("version", version);
	}

	public java.lang.Long getVersion() {
		return get("version");
	}

	public void setAllchildnodeids(java.lang.String allchildnodeids) {
		set("allchildnodeids", allchildnodeids);
	}

	public java.lang.String getAllchildnodeids() {
		return get("allchildnodeids");
	}

	public void setDepartmentlevel(java.lang.Long departmentlevel) {
		set("departmentlevel", departmentlevel);
	}

	public java.lang.Long getDepartmentlevel() {
		return get("departmentlevel");
	}

	public void setDepttype(java.lang.String depttype) {
		set("depttype", depttype);
	}

	public java.lang.String getDepttype() {
		return get("depttype");
	}

	public void setDescription(java.lang.String description) {
		set("description", description);
	}

	public java.lang.String getDescription() {
		return get("description");
	}

	public void setImages(java.lang.String images) {
		set("images", images);
	}

	public java.lang.String getImages() {
		return get("images");
	}

	public void setIsparent(java.lang.String isparent) {
		set("isparent", isparent);
	}

	public java.lang.String getIsparent() {
		return get("isparent");
	}

	public void setNames(java.lang.String names) {
		set("names", names);
	}

	public java.lang.String getNames() {
		return get("names");
	}

	public void setOrderids(java.lang.Long orderids) {
		set("orderids", orderids);
	}

	public java.lang.Long getOrderids() {
		return get("orderids");
	}

	public void setUrl(java.lang.String url) {
		set("url", url);
	}

	public java.lang.String getUrl() {
		return get("url");
	}

	public void setParentdepartmentids(java.lang.String parentdepartmentids) {
		set("parentdepartmentids", parentdepartmentids);
	}

	public java.lang.String getParentdepartmentids() {
		return get("parentdepartmentids");
	}

	public void setPrincipaluserids(java.lang.String principaluserids) {
		set("principaluserids", principaluserids);
	}

	public java.lang.String getPrincipaluserids() {
		return get("principaluserids");
	}

}
