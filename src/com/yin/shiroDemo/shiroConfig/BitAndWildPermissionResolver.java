package yin.shiroDemo.shiroConfig;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * @BelongsProject: shiroDemo
 * @BelongsPackage: yin.shiroDemo.shiroConfig
 * @Author: yinxiucahun
 * @CreateTime: 2019-05-22 09:59
 * @Description: 判断权限匹配
 */
//BitAndWildPermissionResolver 实现了 PermissionResolver 接口，并根据权限字符串是否以
//“+”开头来解析权限字符串为 BitPermission 或 WildcardPermission。
public class BitAndWildPermissionResolver implements PermissionResolver {
    @Override
    public Permission resolvePermission(String permissionString) {
        if (permissionString.startsWith("+")){
            return new BitPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
    }
}
