package com.watt.sys.mvc.product;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.watt.sys.mvc.base.model.BaseSysUser;
import com.watt.sys.mvc.role.SysRole;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class ProductVersion extends BaseSysUser<ProductVersion> {
    public static final ProductVersion dao = new ProductVersion();

    /***
     * 根据用户名查询用户
     * 
     * @param username
     * @return
     */
    public ProductVersion findByUsername(String username) {
        return dao.findFirst("SELECT * from t_product_version u where u.username='" + username + "' and status = 1");
    }

    /***
     * 获取用户所有的角色
     */
    public List<SysRole> getAllRole(String id) {
        return SysRole.dao.find("select r.* from t_product_versionrole u ,sys_role r where u.role_id=r.id and u.user_id='" + id + "'");
    }

    public Page<ProductVersion> getUserPage(Integer curr, Integer pagesize) {
        return ProductVersion.dao.paginate(curr, pagesize, "select * ", " from t_product_version where id !='0'");
    }

    /***
     * 根据主键查询
     */
    public ProductVersion getById(String id) {
        return ProductVersion.dao.findById(id);
    }

}
