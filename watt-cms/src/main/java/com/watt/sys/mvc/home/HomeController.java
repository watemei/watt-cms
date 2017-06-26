/**
 * @author Lion
 * @date 2017年1月24日 下午12:02:35
 * @qq 439635374
 */
package com.watt.sys.mvc.home;

import com.watt.sys.mvc.base.AbstractBaseController;
import com.watt.sys.mvc.menu.SysMenu;
import com.watt.sys.plugin.shiro.ShiroKit;

/***
 * 首页控制器
 */
public class HomeController extends AbstractBaseController {
	/***
	 * 首页
	 */
    public void index(){
    	setAttr("menu", SysMenu.dao.getMenuByUserId(ShiroKit.getLoginUser().getId()));
    	render("/WEB-INF/admin/home/index.html");
    }
    public void getMainPage(){
    	render("/WEB-INF/admin/home/main.html");
    }
    /**
     * 内容页
     * */
    public void content(){
    	render("/WEB-INF/admin/home/content.html");
    }
    /***
     * 获取消息中心最新消息
     */
    public void getSiteMessageTipPage(){
    	render("/WEB-INF/admin/home/siteMessageTip.html");
    }
}
