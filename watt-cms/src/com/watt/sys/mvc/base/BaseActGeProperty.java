package com.watt.sys.mvc.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseActGeProperty<M extends BaseActGeProperty<M>> extends Model<M> implements IBean {

	public void setName(java.lang.String name) {
		set("NAME_", name);
	}

	public java.lang.String getName() {
		return get("NAME_");
	}

	public void setValue(java.lang.String value) {
		set("VALUE_", value);
	}

	public java.lang.String getValue() {
		return get("VALUE_");
	}

	public void setRev(java.lang.Integer rev) {
		set("REV_", rev);
	}

	public java.lang.Integer getRev() {
		return get("REV_");
	}

}
