/**
 * @author Lion
 * @date 2017年1月24日 下午12:02:35
 * @qq 439635374
 */
package com.watt.sys.mvc.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.render.JsonRender;
import com.jflyfox.jfinal.base.BaseController;
import com.jflyfox.jfinal.base.BaseForm;
import com.jflyfox.jfinal.base.Paginator;
import com.jflyfox.jfinal.base.SessionUser;
import com.jflyfox.jfinal.component.util.Attr;
import com.jflyfox.util.Config;
import com.jflyfox.util.DateUtils;
import com.jflyfox.util.HandlerUtils;
import com.jflyfox.util.StrUtils;
import com.watt.sys.dto.RenderBean;
import com.watt.sys.mvc.log.SysLog;
import com.watt.sys.mvc.user.SysUser;

public abstract class AbstractBaseController extends Controller {

	/**
	 * 全局变量
	 */
	protected String ids; // 主键
	protected List<?> list; // 公共list

	protected static String messageSuccess = "操作成功";
	protected static String messageFail = "操作失败";

	/**
	 * 重写renderJson，避免出现IE8下出现下载弹出框
	 */
	@Override
	public void renderJson(Object object) {
		String userAgent = getRequest().getHeader("User-Agent");
		if (userAgent.toLowerCase().indexOf("msie") != -1) {
			render(new JsonRender(object).forIE());
		} else {
			super.renderJson(object);
		}
	}

	/**
	 * 解决IE8下下载失败的问题
	 */
	@Override
	public void renderFile(File file) {
		String userAgent = getRequest().getHeader("User-Agent");
		if (userAgent.toLowerCase().indexOf("msie") != -1) {
			getResponse().reset();
		}
		super.renderFile(file);
	}

	/**
	 * 解决IE8下下载失败的问题
	 */
	public void renderFile(File file, String downloadSaveFileName) {
		String userAgent = getRequest().getHeader("User-Agent");
		if (userAgent.toLowerCase().indexOf("msie") != -1) {
			getResponse().reset();
		}
		renderFile(file, downloadSaveFileName);
	}

	/***
	 * 分页数据
	 */
	public void renderPage(Object data, String msg, int count) {
		RenderBean renderBean = new RenderBean();
		renderBean.setSuccess(true);
		renderBean.setData(data);
		renderBean.setMessage(msg);
		renderBean.setCount(count);
		renderJson(renderBean);
	}

	/**
	 * 自定义render
	 * 
	 * @param code
	 *            状态码
	 * @param data
	 *            返回数据
	 * @param description
	 *            描述
	 *            描述：公共render，所有的renderJson都必须返回RenderObject，包含处理状态、返回数据、失败下的状态码
	 *            、失败描述
	 */
	public void renderSuccess(Object data, String description) {
		RenderBean renderBean = new RenderBean();
		renderBean.setSuccess(true);
		renderBean.setData(data);
		renderBean.setMessage(description);
		renderJson(renderBean);
	}

	public void renderSuccess(Object data) {
		RenderBean renderBean = new RenderBean();
		renderBean.setSuccess(true);
		renderBean.setData(data);
		renderBean.setMessage(messageSuccess);
		renderJson(renderBean);
	}

	public void renderSuccess(String message) {
		RenderBean renderBean = new RenderBean();
		renderBean.setSuccess(true);
		renderBean.setMessage(message);
		renderJson(renderBean);
	}

	public void renderSuccess() {
		RenderBean renderBean = new RenderBean();
		renderBean.setSuccess(true);
		renderBean.setMessage(messageSuccess);
		renderJson(renderBean);
	}

	/**
	 * 自定义render失败
	 * 
	 * @param code
	 *            状态码
	 * @param data
	 *            返回数据
	 * @param description
	 *            描述
	 *            描述：公共render，所有的renderJson都必须返回RenderObject，包含处理状态、返回数据、失败下的状态码
	 *            、失败描述
	 */
	public void renderError(Object data, String description) {
		RenderBean renderBean = new RenderBean();
		renderBean.setSuccess(false);
		renderBean.setMessage(description);
		renderJson(renderBean);
	}

	public void renderError(Object data) {
		RenderBean renderBean = new RenderBean();
		renderBean.setSuccess(false);
		renderBean.setMessage(messageFail);
		renderJson(renderBean);
	}

	public void renderError(String message) {
		RenderBean renderBean = new RenderBean();
		renderBean.setSuccess(false);
		renderBean.setMessage(message);
		renderJson(renderBean);
	}

	public void renderError() {
		RenderBean renderBean = new RenderBean();
		renderBean.setSuccess(false);
		renderBean.setMessage(messageFail);
		renderJson(renderBean);
	}

	/**
	 * 表单数组映射List<Model>
	 * 
	 * @param modelClass
	 * @return
	 */
	public <T extends AbstractBaseModel<T>> List<T> getModels(Class<? extends T> modelClass) {
		String modelName = modelClass.getSimpleName();
		String prefix = StrKit.firstCharToLowerCase(modelName) + "List";
		return getModels(modelClass, prefix, false);
	}

	/**
	 * 表单数组映射List<Model>
	 * 
	 * @param modelClass
	 * @param skipConvertError
	 * @return
	 */
	public <T extends AbstractBaseModel<T>> List<T> getModels(Class<? extends T> modelClass, boolean skipConvertError) {
		String modelName = modelClass.getSimpleName();
		String prefix = StrKit.firstCharToLowerCase(modelName) + "List";
		return getModels(modelClass, prefix, skipConvertError);
	}

	/**
	 * 表单数组映射List<Model>
	 * 
	 * @param modelClass
	 * @param prefix
	 * @return
	 */
	public <T extends AbstractBaseModel<T>> List<T> getModels(Class<? extends T> modelClass, String prefix) {
		return getModels(modelClass, prefix, false);
	}

	/**
	 * 表单数组映射List<Model>
	 * 
	 * @param modelClass
	 * @param prefix
	 * 
	 *            描述：
	 * 
	 *            表单 <input type="hidden" name="groupList[0].ids" value="111"/>
	 *            <input type="text" name="groupList[0].names" value="222"/>
	 * 
	 *            <input type="hidden" name="groupList[1].ids" value="333"/>
	 *            <input type="text" name="groupList[1].names" value="444"/>
	 * 
	 *            <input type="hidden" name="groupList[3].ids" value="555"/>
	 *            <input type="text" name="groupList[3].names" value="666"/>
	 * 
	 *            调用方法 List<Group> groupList =
	 *            ToolModelInjector.injectModels(getRequest(), Group.class,
	 *            "groupList");
	 * 
	 *            // 默认的prefix是Model类的首字母小写加上List List<Group> groupList =
	 *            ToolModelInjector.injectModels(getRequest(), Group.class);
	 */
	public <T extends AbstractBaseModel<T>> List<T> getModels(Class<? extends T> modelClass, String prefix,
			boolean skipConvertError) {
		int maxIndex = 0; // 最大的数组索引
		boolean zeroIndex = false; // 是否存在0索引

		String arrayPrefix = prefix + "[";
		String key = null;
		Enumeration<String> names = getRequest().getParameterNames();
		while (names.hasMoreElements()) {
			key = names.nextElement();
			if (key.startsWith(arrayPrefix) && key.indexOf("]") != -1) {
				int indexTemp = Integer.parseInt(key.substring(key.indexOf("[") + 1, key.indexOf("]")));

				if (indexTemp == 0) {
					zeroIndex = true; // 是否存在0索引
				}

				if (indexTemp > maxIndex) {
					maxIndex = indexTemp; // 找到最大的数组索引
				}
			}
		}

		List<T> modelList = new ArrayList<T>();
		for (int i = 0; i <= maxIndex; i++) {
			if ((i == 0 && zeroIndex) || i != 0) { // 避免表单空值时调用产生一个无用的值
				T baseModel = (T) getModel(modelClass, prefix + "[" + i + "]", skipConvertError);
				modelList.add(baseModel);
			}
		}

		return modelList;
	}

	/**
	 * 表单数组映射List<Bean>
	 * 
	 * @param beanClass
	 * @return
	 */
	public <T> List<T> getBeans(Class<T> beanClass) {
		String modelName = beanClass.getSimpleName();
		String prefix = StrKit.firstCharToLowerCase(modelName) + "List";
		return getBeans(beanClass, prefix, false);
	}

	/**
	 * 表单数组映射List<Bean>
	 * 
	 * @param beanClass
	 * @param skipConvertError
	 * @return
	 */
	public <T> List<T> getBeans(Class<T> beanClass, boolean skipConvertError) {
		String modelName = beanClass.getSimpleName();
		String prefix = StrKit.firstCharToLowerCase(modelName) + "List";
		return getBeans(beanClass, prefix, skipConvertError);
	}

	/**
	 * 表单数组映射List<Bean>
	 * 
	 * @param beanClass
	 * @param prefix
	 * @return
	 */
	public <T> List<T> getBeans(Class<T> beanClass, String prefix) {
		return getBeans(beanClass, prefix, false);
	}

	/**
	 * 表单数组映射List<Bean>
	 * 
	 * @param beanClass
	 * @param prefix
	 */
	public <T> List<T> getBeans(Class<T> beanClass, String prefix, boolean skipConvertError) {
		int maxIndex = 0; // 最大的数组索引
		boolean zeroIndex = false; // 是否存在0索引

		String arrayPrefix = prefix + "[";
		String key = null;
		Enumeration<String> names = getRequest().getParameterNames();
		while (names.hasMoreElements()) {
			key = names.nextElement();
			if (key.startsWith(arrayPrefix) && key.indexOf("]") != -1) {
				int indexTemp = Integer.parseInt(key.substring(key.indexOf("[") + 1, key.indexOf("]")));

				if (indexTemp == 0) {
					zeroIndex = true; // 是否存在0索引
				}

				if (indexTemp > maxIndex) {
					maxIndex = indexTemp; // 找到最大的数组索引
				}
			}
		}

		List<T> beanList = new ArrayList<T>();
		for (int i = 0; i <= maxIndex; i++) {
			if ((i == 0 && zeroIndex) || i != 0) { // 避免表单空值时调用产生一个无用的值
				T baseModel = (T) getBean(beanClass, prefix + "[" + i + "]", skipConvertError);
				beanList.add(baseModel);
			}
		}

		return beanList;
	}

	/**
	 * 表单数组映射List<Model>
	 * 
	 * @param modelClass
	 * @return
	 */
	public <T extends AbstractBaseModel<T>> Controller keepModels(Class<? extends T> modelClass) {
		String modelName = StrKit.firstCharToLowerCase(modelClass.getSimpleName());
		return keepModels(modelClass, modelName, false);
	}

	/**
	 * 表单数组映射List<Model>
	 * 
	 * @param modelClass
	 * @param skipConvertError
	 * @return
	 */
	public <T extends AbstractBaseModel<T>> Controller keepModels(Class<? extends T> modelClass,
			boolean skipConvertError) {
		String modelName = StrKit.firstCharToLowerCase(modelClass.getSimpleName());
		return keepModels(modelClass, modelName, skipConvertError);
	}

	/**
	 * 表单数组映射List<Model>
	 * 
	 * @param modelClass
	 * @param modelName
	 * @return
	 */
	public <T extends AbstractBaseModel<T>> Controller keepModels(Class<? extends T> modelClass, String modelName) {
		return keepModels(modelClass, modelName, false);
	}

	/**
	 * 表单数组映射List<Model>
	 * 
	 * @param modelClass
	 * @param modelName
	 * @param skipConvertError
	 * @return
	 */
	public <T extends AbstractBaseModel<T>> Controller keepModels(Class<? extends T> modelClass, String modelName,
			boolean skipConvertError) {
		if (StrKit.notBlank(modelName)) {
			List<T> model = getModels(modelClass, modelName, skipConvertError);
			setAttr(modelName, model);
		} else {
			keepPara();
		}
		return this;
	}

	/**
	 * 表单数组映射List<Bean>
	 * 
	 * @param beanClass
	 * @return
	 */
	public <T> Controller keepBeans(Class<T> beanClass) {
		String modelName = StrKit.firstCharToLowerCase(beanClass.getSimpleName());
		return keepBeans(beanClass, modelName, false);
	}

	/**
	 * 表单数组映射List<Bean>
	 * 
	 * @param beanClass
	 * @param skipConvertError
	 * @return
	 */
	public <T> Controller keepBeans(Class<T> beanClass, boolean skipConvertError) {
		String modelName = StrKit.firstCharToLowerCase(beanClass.getSimpleName());
		return keepBeans(beanClass, modelName, skipConvertError);
	}

	/**
	 * 表单数组映射List<Bean>
	 * 
	 * @param beanClass
	 * @param beanName
	 * @return
	 */
	public <T> Controller keepBeans(Class<T> beanClass, String beanName) {
		return keepBeans(beanClass, beanName, false);
	}

	/**
	 * 表单数组映射List<Bean>
	 * 
	 * @param beanClass
	 * @param beanName
	 * @param skipConvertError
	 * @return
	 */
	public <T> Controller keepBeans(Class<T> beanClass, String beanName, boolean skipConvertError) {
		if (StrKit.notBlank(beanName)) {
			List<T> model = getBeans(beanClass, beanName, skipConvertError);
			setAttr(beanName, model);
		} else {
			keepPara();
		}
		return this;
	}

	/**
	 * 获取checkbox值，数组
	 * 
	 * @param name
	 * @return
	 */
	protected String[] getParas(String name) {
		return getRequest().getParameterValues(name);
	}

	/**
	 * 用户登录，登出记录
	 * 
	 * 2015年10月16日 下午2:36:39 flyfox 369191470@qq.com
	 * 
	 * @param user
	 * @param operType
	 */
	protected void saveLog(SysUser user, String operType) {
		try {
			String tableName = user.getTable().getName();
			Integer updateId = user.getInt("update_id");
			String updateTime = user.getStr("update_time");
			String sql = "INSERT INTO `sys_log` ( `log_type`, `oper_object`, `oper_table`," //
					+ " `oper_id`, `oper_type`, `oper_remark`, `create_time`, `create_id`) " //
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			Db.update(sql, SysLog.TYPE_SYSTEM, SysLog.getTableRemark(tableName), tableName, //
					updateId, operType, "", updateTime, updateId);
		} catch (Exception e) {
			// log.error("添加日志失败", e);
		}
	}

	protected static final String page_message = Config.getStr("PAGES.MESSAGE");
	protected static final Log log = Log.getLog(BaseController.class);

	protected void renderMessage(String message) {
		renderMessage(message, "closeIframe();");
	}

	protected void renderMessageByFailed(String message) {
		renderMessage(message, "history.back();");
	}

	protected void renderMessage(String message, String obj) {
		String script = "";
		if (StrUtils.isEmpty(obj)) { // 关闭页面
			script = "closeIframe();";
		} else if (script.endsWith(".jsp")) { // 页面跳转
			script = "window.location.href = \"" + obj + "\"";
		} else { // 直接执行JS
			script = obj;
		}
		setAttr("msg", message);
		setAttr("script", script);
		render(page_message);
	}

	protected void render500(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/pages/error/500.jsp").forward(request, response);
		} catch (Exception e) {
			log.error("500 page -->", e);
		}
	}

	/**
	 * 获取前一个页面
	 * 
	 * 2015年3月25日 下午3:47:55 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	protected String getPrePage() {
		return getRequest().getHeader("Referer");
	}

	/**
	 * 获取当前时间，保存创建时间使用
	 * 
	 * 2015年3月25日 下午3:48:02 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	protected String getNow() {
		return DateUtils.getNow(DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS);
	}

	protected <T> T handler(Class<T> modelClass) {
		Object o = null;
		try {
			o = modelClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return handler(o);
	}

	@SuppressWarnings("unchecked")
	protected <T> T handler(Object o) {
		if (o == null) {
			return null;
		}
		return (T) HandlerUtils.handler(getRequest(), o);
	}

	public void renderAuto(String view) {
		String path = getAutoPath(view);

		super.render(path);
	}

	public void redirectAuto(String view) {
		String path = getAutoPath(view);

		super.redirect(path);
	}

	protected String getAutoPath(String view) {
		String path = view;

		if (!view.startsWith("/")) {
			path = "/" + path;
		}

		path = (isMoblie() ? Attr.PATH_MOBILE : Attr.PATH_PC) + path;

		if (view.startsWith("/")) {
			path = "/" + path;
		}

		path = path.replace("//", "/");
		return path;
	}

	public boolean isMoblie() {
		return getSessionAttr(Attr.SESSION_IS_MOILE);
	}

	@SuppressWarnings("rawtypes")
	public SessionUser getSessionUser() {
		return getSessionAttr(Attr.SESSION_NAME);
	}

	@SuppressWarnings("rawtypes")
	public SessionUser setSessionUser(SessionUser user) {
		setSessionAttr(Attr.SESSION_NAME, user);
		return user;
	}

	public void removeSessionUser() {
		removeSessionAttr(Attr.SESSION_NAME);
	}

	public Paginator getPaginator() {
		Paginator paginator = new Paginator();
		Integer pageNo = getParaToInt("pageNo");
		if (pageNo != null && pageNo > 0) {
			paginator.setPageNo(pageNo);
		}
		Integer pageSize = getParaToInt("recordsperpage");
		if (pageSize != null && pageSize > 0) {
			paginator.setPageSize(pageSize);
		}
		return paginator;
	}

	public Object[] toArray(List<Object> list) {
		return list.toArray(new Object[list.size()]);
	}

	public BaseForm getBaseForm() {
		BaseForm form = super.getAttr("form");
		return form == null ? new BaseForm() : form;
	}

	/**
	 * 原始的GetModel方法
	 * 
	 * 2015年2月25日 上午11:02:37 flyfox 330627517@qq.com
	 * 
	 * @param modelClass
	 * @return
	 */
	public <T> T getModelByOld(Class<T> modelClass) {
		return super.getModel(modelClass);
	}

	/**
	 * 覆盖原始方法，采用PAGE_MODEL_NAME做为前缀
	 */
	public <T> T getModelByName(Class<T> modelClass) {
		return super.getModel(modelClass, Attr.PAGE_MODEL_NAME);
	}

	/**
	 * 新GetModel方法，采用PAGE_ATTR_NAME，作为前缀
	 * 
	 * 2015年2月25日 上午11:03:45 flyfox 330627517@qq.com
	 * 
	 * @param modelClass
	 * @return
	 */
	public <T> T getModelByAttr(Class<T> modelClass) {
		return super.getModel(modelClass, Attr.PAGE_ATTR_NAME);
	}

	/**
	 * 新GetModel方法，采用PAGE_FORM_NAME，作为前缀
	 * 
	 * 2015年10月23日 上午11:03:45 flyfox 330627517@qq.com
	 * 
	 * @param modelClass
	 * @return
	 */
	public <T> T getModelByForm(Class<T> modelClass) {
		return super.getBean(modelClass, Attr.PAGE_FORM_NAME);
	}

}
