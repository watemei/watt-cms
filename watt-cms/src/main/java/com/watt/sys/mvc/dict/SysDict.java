package com.watt.sys.mvc.dict;

import com.jflyfox.jfinal.component.annotation.ModelBind;
import com.watt.sys.mvc.base.AbstractBaseModel;

@ModelBind(table = "sys_dict", key = "dict_id")
public class SysDict extends AbstractBaseModel<SysDict> {

	private static final long serialVersionUID = 1L;
	public static final SysDict dao = new SysDict();

}