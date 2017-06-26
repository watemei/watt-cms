/**
 * 
 */
package com.watt.sys.mvc.workflow.model;

import com.jfinal.plugin.activerecord.Page;
import com.watt.sys.mvc.base.AbstractBaseController;

/**
 * @author Lion
 * @date 2017年2月16日 下午4:04:25
 * @qq 439635374
 */

public class ModelController extends AbstractBaseController{
	/***
	 * 获得列表页
	 */
	public void getListPage(){
    	render("/WEB-INF/admin/workflow/model/list.html");
    }
    /***
     * 查询分页数据
     */
    public void listData(){
    	String curr = getPara("pageIndex");
    	String pageSize = getPara("pageSize");
    	Page<ActReModel> page = ActReModel.dao.getModelPage(Integer.valueOf(curr),Integer.valueOf(pageSize));
    	renderPage(page.getList(),"" ,page.getTotalRow());
    }
}
