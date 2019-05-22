package yin.shiroDemo.shiroConfig;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @BelongsProject: shiroDemo
 * @BelongsPackage: yin.shiroDemo.shiroConfig
 * @Author: yinxiucahun
 * @CreateTime: 2019-05-22 10:20
 * @Description: 自定义Realm
 */
public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //SimpleAuthorizationInfo 简单的授权信息
        //SimpleAuthenticationInfo 简单认证信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("role1");
        authorizationInfo.addRole("role2");
        authorizationInfo.addObjectPermission(new BitPermission("+user1+10"));
        authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));
        authorizationInfo.addStringPermission("+user2+10");
        authorizationInfo.addStringPermission("user2:*");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        //得到密码
        String password = new String((char[])token.getCredentials());
        if(!"zhang".equals(username)){
            //用户名错误
            throw new UnknownAccountException();
        }
        if(!"123".equals(password)){
            //密码错误
            throw new IncorrectCredentialsException();
        }
        //如果身份认证验证成功，返回一个 AuthenticationInfo 实现；
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
