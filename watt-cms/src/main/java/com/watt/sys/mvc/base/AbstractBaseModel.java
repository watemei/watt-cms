/**
 * @author Lion
 * @date 2017年1月24日 下午12:02:35
 * @qq 439635374
 */
package com.watt.sys.mvc.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;
import com.jflyfox.jfinal.base.BaseModel;
import com.jflyfox.util.DateUtils;
import com.jflyfox.util.NumberUtils;
import com.watt.sys.mvc.log.SysLog;

/**
 * Model基础类
 * 
 * @param <M>
 * 
 *            抽取公共方法，并重写save、update、getDate方法
 */
public abstract class AbstractBaseModel<M extends Model<M>> extends BaseModel<M> {

	private static final long serialVersionUID = -900378319414539856L;

	private static final Log log = Log.getLog(AbstractBaseModel.class);

	/**
	 * 字段描述：版本号 字段类型 ：bigint
	 */
	public static final String column_version = "version";

	/**
	 * sqlId : platform.baseModel.select 描述：通用查询
	 */
	public static final String sqlId_select = "platform.baseModel.select";

	/**
	 * sqlId : platform.baseModel.update 描述：通用更新
	 */
	public static final String sqlId_update = "platform.baseModel.update";

	/**
	 * sqlId : platform.baseModel.delete 描述：通用删除
	 */
	public static final String sqlId_delete = "platform.baseModel.delete";

	/**
	 * sqlId : platform.baseModel.deleteIn 描述：通用删除
	 */
	public static final String sqlId_deleteIn = "platform.baseModel.deleteIn";

	/**
	 * sqlId : platform.baseModel.deleteOr 描述：通用删除
	 */
	public static final String sqlId_deleteOr = "platform.baseModel.deleteOr";

	/**
	 * sqlId : platform.baseModel.splitPageSelect 描述：通用select *
	 */
	public static final String sqlId_splitPageSelect = "platform.baseModel.splitPageSelect";

	/**
	 * 获取表映射对象
	 * 
	 * @return
	 */
	public Table getTable() {
		return TableMapping.me().getTable(getClass());
	}

	/**
	 * 获取表名称
	 * 
	 * @return
	 */
	public String getTableName() {
		return getTable().getName();
	}

	/**
	 * 获取主键名称
	 * 
	 * @return
	 */
	public String[] getPKNameArr() {
		return getTable().getPrimaryKey();
	}

	/**
	 * 获取主键名称
	 * 
	 * @return
	 */
	public String getPKNameStr() {
		String[] pkArr = getPKNameArr();
		if (pkArr.length == 1) {
			return pkArr[0];
		} else {
			String pk = "";
			for (String pkName : pkArr) {
				pk += pkName + ",";
			}
			return pk;
		}

	}

	/**
	 * 获取主键值：非复合主键
	 * 
	 * @return
	 */
	public String getPKValue() {
		String[] pkNameArr = getTable().getPrimaryKey();
		if (pkNameArr.length == 1) {
			return this.getStr(pkNameArr[0]);
		} else {
			String pk = "";
			for (String pkName : pkNameArr) {
				pk += this.get(pkName) + ",";
			}
			return pk;
		}
	}

	/**
	 * 获取主键值：复合主键
	 * 
	 * @return
	 */
	public List<Object> getPKValueList() {
		String[] pkNameArr = getTable().getPrimaryKey();

		List<Object> pkValueList = new ArrayList<Object>();
		for (String pkName : pkNameArr) {
			pkValueList.add(this.get(pkName));
		}

		return pkValueList;
	}


	/**
	 * 重写save方法，单主键，自定义主键值
	 */
	public boolean save(String pkIds) {
		String[] pkArr = getTable().getPrimaryKey();

		this.set(pkArr[0], pkIds); // 设置主键值

		if (getTable().hasColumnLabel(column_version)) { // 是否需要乐观锁控制
			this.set(column_version, Long.valueOf(0)); // 初始化乐观锁版本号
		}

		return super.save();
	}

	/**
	 * 重写save方法，复合主键，自定义主键值
	 */
	public boolean save(Map<String, Object> pkMap) {
		Set<String> pkSet = pkMap.keySet();
		for (String pk : pkSet) {
			this.set(pk, pkMap.get(pk)); // 设置主键值
		}

		if (getTable().hasColumnLabel(column_version)) { // 是否需要乐观锁控制
			this.set(column_version, Long.valueOf(0)); // 初始化乐观锁版本号
		}

		return super.save();
	}

	/****************************************** 加入公共日志 ******************************************/
	@Override
	public boolean save() {
		boolean flag = super.save();
		String tableName = getTable().getName();
		String[] keys = getTable().getPrimaryKey();
		Object id = null;
		if (keys != null && keys.length >= 1) {
			id = get(keys[0]);
		}
		Integer primaryId = (id != null) ? NumberUtils.parseInt(id) : null;
		saveLog(tableName, primaryId, SysLog.MODEL_SAVE);
		return flag;
	}

	@Override
	public boolean delete() {
		boolean flag = super.delete();
		String tableName = getTable().getName();
		String[] keys = getTable().getPrimaryKey();
		Object id = null;
		if (keys != null && keys.length >= 1) {
			id = get(keys[0]);
		}
		Integer primaryId = (id != null) ? NumberUtils.parseInt(id) : null;
		saveLog(tableName, primaryId, SysLog.MODEL_DELETE);
		return flag;
	}

	@Override
	public boolean deleteById(Object id) {
		boolean flag = super.deleteById(id);
		String tableName = getTable().getName();
		Integer primaryId = (id != null) ? NumberUtils.parseInt(id) : null;
		saveLog(tableName, primaryId, SysLog.MODEL_DELETE);
		return flag;
	}

	@Override
	public boolean update() {
		boolean flag = super.update();
		String tableName = getTable().getName();
		String[] keys = getTable().getPrimaryKey();
		Object id = null;
		if (keys != null && keys.length >= 1) {
			id = get(keys[0]);
		}
		Integer primaryId = (id != null) ? NumberUtils.parseInt(id) : null;
		saveLog(tableName, primaryId, SysLog.MODEL_UPDATE);
		return flag;
	}

	protected void saveLog(String tableName, Integer primaryId, String operType) {
		try {
			Integer updateId = getAttrs().containsKey("update_id") ? getInt("update_id") : 0;
			String updateTime = getAttrs().containsKey("update_id") ? getStr("update_time")
					: DateUtils.getNow(DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS);
			String sql = "INSERT INTO `sys_log` ( `log_type`, `oper_object`, `oper_table`," //
					+ " `oper_id`, `oper_type`, `oper_remark`, `create_time`, `create_id`) " //
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			Db.update(sql, SysLog.TYPE_MODEL, SysLog.getTableRemark(tableName), tableName, //
					primaryId, operType, "", updateTime, updateId);
		} catch (Exception e) {
			log.error("添加日志失败", e);
		}
	}

}
