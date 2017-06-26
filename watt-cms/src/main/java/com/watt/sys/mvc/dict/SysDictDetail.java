package com.watt.sys.mvc.dict;

import com.jflyfox.jfinal.component.annotation.ModelBind;
import com.watt.sys.mvc.base.AbstractBaseModel;

@ModelBind(table = "sys_dict_detail", key = "detail_id")
public class SysDictDetail extends AbstractBaseModel<SysDictDetail> {

	private static final long serialVersionUID = 1L;
	public static final SysDictDetail dao = new SysDictDetail();

}
