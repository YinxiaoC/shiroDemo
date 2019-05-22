package yin.shiroDemo.shiroConfig;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;

/**
 * @BelongsProject: shiroDemo
 * @BelongsPackage: yin.shiroDemo.shiroConfig
 * @Author: yinxiucahun
 * @CreateTime: 2019-05-22 10:15
 * @Description: 根据角色字符串来解析得到权限集合
 */
//如果用户拥有 role1，那么就返回一个“menu:*”的权限。
public class MyRolePermissionResolver implements RolePermissionResolver {
    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        if ("role1".equals(roleString)){
            return Arrays.asList((Permission)new WildcardPermission("menu:*"));
        }
        return null;
    }
}
