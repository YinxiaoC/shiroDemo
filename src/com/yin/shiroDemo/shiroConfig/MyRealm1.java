package yin.shiroDemo.shiroConfig;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @BelongsProject: shiroDemo
 * @BelongsPackage: yin.shiroDemo.shiroConfig
 * @Author: yinxiucahun
 * @CreateTime: 2019-05-21 15:08
 * @Description: 单Realm配置
 */
public class MyRealm1 implements Realm {
    @Override
    public String getName() {
        return "myRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken
        // instanceof 判断其左边对象是否为其右边类的实例，返回boolean类型的数据
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //得到用户名
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
