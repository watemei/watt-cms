/**
 * @author Lion
 * @date 2017年1月24日 下午12:02:35
 * @qq 439635374
 */
package com.watt.sys.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.template.Engine;
import com.watt.sys.handler.GlobalHandler;
import com.watt.sys.mvc.base.model._MappingKit;
import com.watt.sys.mvc.home.HomeController;
import com.watt.sys.mvc.login.LoginController;
import com.watt.sys.mvc.menu.MenuController;
import com.watt.sys.mvc.module.ModuleController;
import com.watt.sys.mvc.operate.OperateController;
import com.watt.sys.mvc.role.RoleController;
import com.watt.sys.mvc.user.UserController;
import com.watt.sys.mvc.workflow.WorkFlowController;
import com.watt.sys.mvc.workflow.main.StencilsetRestResource;
import com.watt.sys.mvc.workflow.model.ModelController;
import com.watt.sys.mvc.workflow.model.ModelEditorJsonRestResource;
import com.watt.sys.mvc.workflow.model.ModelSaveRestResource;
import com.watt.sys.mvc.workflow.rest.ProcessDefinitionDiagramLayoutResource;
import com.watt.sys.mvc.workflow.rest.ProcessInstanceDiagramLayoutResource;
import com.watt.sys.mvc.workflow.rest.ProcessInstanceHighlightsResource;
import com.watt.sys.plugin.activiti.ActivitiPlugin;
import com.watt.sys.plugin.shiro.ShiroInterceptor;
import com.watt.sys.plugin.shiro.ShiroKit;
import com.watt.sys.plugin.shiro.ShiroPlugin;

public class MainConfig extends JFinalConfig {
    Routes routes;
    
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}
	/**
	 * 配置JFinal常量
	 */
	@Override
	public void configConstant(Constants me) {
		//读取数据库配置文件
		PropKit.use("config.properties");
		//设置当前是否为开发模式
		me.setDevMode(PropKit.getBoolean("devMode"));
		//设置默认上传文件保存路径 getFile等使用
		me.setBaseUploadPath("upload/");
		//设置上传最大限制尺寸
		me.setMaxPostSize(1024*1024*10);
		//获取beetl模版引擎
//		me.setRenderFactory(new BeetlRenderFactory());
		me.setError404View("/error/404.html");
        // 获取GroupTemplate ,可以设置共享变量等操作
//        @SuppressWarnings("unused")
//		GroupTemplate groupTemplate = BeetlRenderFactory.groupTemplate ;
	}

	/**
	 * 配置JFinal插件
	 * 数据库连接池
	 * ORM
	 * 缓存等插件
	 * 自定义插件
	 */
	@Override
	public void configPlugin(Plugins me) {
		//配置数据库连接池插件
		C3p0Plugin c3p0Plugin=new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
		//orm映射 配置ActiveRecord插件
		ActiveRecordPlugin arp=new ActiveRecordPlugin(c3p0Plugin);
		arp.setShowSql(PropKit.getBoolean("devMode"));
		arp.setDialect(new MysqlDialect());
		//流程引擎
		ActivitiPlugin acitivitiPlugin = new ActivitiPlugin();
		_MappingKit.mapping(arp);
		//添加到插件列表中
		me.add(c3p0Plugin);
		me.add(arp);
		me.add(acitivitiPlugin);
		ShiroPlugin shiroPlugin = new ShiroPlugin(this.routes);
	    shiroPlugin.setLoginUrl("/login.html");//登陆url：未验证成功跳转
	    shiroPlugin.setSuccessUrl("/admin/index");//登陆成功url：验证成功自动跳转
	    shiroPlugin.setUnauthorizedUrl("/admin/login/needPermission");//授权url：未授权成功自动跳转
	    me.add(shiroPlugin);
	}
	
	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		 me.add(new ShiroInterceptor());//shiro拦截器
	}
	
	/**
	 * 配置全局处理器
	 */
	@Override
	public void configHandler(Handlers handler) {
		//log.info("configHandler 全局配置处理器，设置跳过哪些URL不处理");
//		handler.add(new UrlSkipHandler("/ca/.*|/se/.*|.*.htm|.*.html|.*.js|.*.css|.*.json|.*.png|.*.gif|.*.jpg|.*.jpeg|.*.bmp|.*.ico|.*.exe|.*.txt|.*.zip|.*.rar|.*.7z", false));
		handler.add(new GlobalHandler());
	}
	

	
	/**
	 * 配置JFinal路由映射
	 */
	@Override
	public void configRoute(Routes me) {
		this.routes = me;//shiro使用
		//功能路由
		me.add("/admin/login", LoginController.class);//登陆
		me.add("/admin/home", HomeController.class);//首页
		me.add("/admin/user", UserController.class);//用户
		me.add("/admin/menu", MenuController.class);//菜单
		me.add("/admin/workflow",WorkFlowController.class);//工作流
		me.add("/admin/model",ModelController.class);//工作流-模型
		me.add("/admin/module",ModuleController.class);//模块
		me.add("/admin/operate",OperateController.class);//执行对象-功能
		me.add("/admin/role",RoleController.class);//执行对象-功能
		//流程在线编辑器和流程跟踪所用路由
		me.add("/admin/process-instance/highlights",ProcessInstanceHighlightsResource.class);//modeler
		me.add("/admin/process-instance/diagram-layout",ProcessInstanceDiagramLayoutResource.class);//modeler
		me.add("/admin/process-definition/diagram-layout",ProcessDefinitionDiagramLayoutResource.class);//modeler
		me.add("/admin/model/save",ModelSaveRestResource.class);
		me.add("/admin/editor/stencilset",StencilsetRestResource.class);
		me.add("/admin/model/json",ModelEditorJsonRestResource.class);
	}
	
	@Override
	public void configEngine(Engine me) {
		me.addSharedObject("shiro",new ShiroKit());
	}
}
