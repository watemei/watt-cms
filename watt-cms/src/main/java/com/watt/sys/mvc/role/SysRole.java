package com.watt.sys.mvc.role;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.watt.sys.mvc.base.model.BaseSysRole;
import com.watt.sys.tool.UuidUtil;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class SysRole extends BaseSysRole<SysRole> {
	public static final SysRole dao = new SysRole();
	/***
	 * 根据主键查询
	 */
	public SysRole getById(String id){
		return SysRole.dao.findById(id);
	}
	/***
	 * 查询分页
	 * @param curr
	 * @param pagesize
	 * @return
	 */
	public Page<SysRole> getRolePage(Integer curr , Integer pagesize){
		return SysRole.dao.paginate(curr, pagesize, "select * ", " from sys_role");
	}
	/***
	 * 删除角色下所有权限
	 * @param roleid
	 * @return
	 */
	public Integer deleteAllOperatePermisstion(String roleid){
		return Db.update("delete from sys_roleoperator where role_id='"+roleid+"'");
	}
	/***
	 * 给角色赋权
	 * @param data
	 * @return
	 */
	public Boolean addRolePermisstion(String roleid , String data){
		if(StringUtils.isNotBlank(data)){
			deleteAllOperatePermisstion(roleid);//删除角色下所有权限
			String darr[] = data.split(",");//添加权限
			for(String d:darr){
				SysRoleoperator ro = new SysRoleoperator();
				ro.setId(UuidUtil.getUUID());
				ro.setOperatorId(d);
				ro.setRoleId(roleid);
				ro.save();
			}
		}
		return true;
	}
}
