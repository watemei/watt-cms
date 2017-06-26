/**
 * @author Lion
 * @date 2017年1月24日 下午12:02:35
 * @qq 439635374
 */
package com.watt.sys.mvc.user;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.watt.sys.mvc.base.AbstractBaseController;
import com.watt.sys.mvc.role.SysRole;
import com.watt.sys.tool.UuidUtil;

/***
 * 用户管理控制器
 * 
 * @author Administrator
 *
 */
public class UserController extends AbstractBaseController {

    /***
     * 获得列表页
     */
    public void getListPage() {
        render("/WEB-INF/admin/user/list.html");
    }

    /***
     * 查询分页数据
     */
    public void listData() {
        String curr = getPara("pageIndex");
        String pageSize = getPara("pageSize");
        Page<SysUser> page = SysUser.dao.getUserPage(Integer.valueOf(curr), Integer.valueOf(pageSize));
        renderPage(page.getList(), "", page.getTotalRow());
    }

    /***
     * 保存
     */
    public void save() {
        SysUser user = getModel(SysUser.class);
        if (StringUtils.isNotBlank(user.getId())) {
            PasswordService svc = new DefaultPasswordService();
            String encrypted = svc.encryptPassword(user.get("password"));
            user.set("password", encrypted);
            user.update();
        } else {
            user.setId(UuidUtil.getUUID());
            PasswordService svc = new DefaultPasswordService();
            String encrypted = svc.encryptPassword(user.get("password"));
            user.set("password", encrypted);
            user.save();
        }
        renderSuccess();
    }

    /***
     * 获取编辑页面
     */
    public void getEditPage() {
        // 添加和修改
        String id = getPara("id");
        if (StringUtils.isNotBlank(id)) {
            SysUser user = SysUser.dao.getById(id);
            user.set("password", null);
            setAttr("user", user);
        }
        render("/WEB-INF/admin/user/edit.html");
    }

    /***
     * 给用户绑定角色
     */
    public void operateRole() {
        setAttr("id", getPara("id"));
        render("/WEB-INF/admin/user/operateRole.html");
    }

    /***
     * 给用户绑定一个角色
     */
    public void addRoleToUser() {
        String uid = getPara("uid");
        String roleid = getPara("roleid");
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.deleteByUidAndRoleId(uid, roleid);
        sysUserRole.set("user_id", uid);
        sysUserRole.set("role_id", roleid);
        sysUserRole.setId(UuidUtil.getUUID());
        sysUserRole.save();
        renderSuccess();
    }

    /***
     * 删除
     * 
     * @throws Exception
     */
    @Before(Tx.class)
    public void delete() throws Exception {
        String ids = getPara("ids");
        String idarr[] = ids.split(",");
        for (String id : idarr) {
            SysUser sysUser = SysUser.dao.getById(id);
            if (sysUser != null) {
                sysUser.delete();// 删除
            }
        }
        renderSuccess();
    }

}
