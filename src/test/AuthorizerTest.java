import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import yin.shiroDemo.shiroConfig.MyRolePermissionResolver;

/**
 * @BelongsProject: shiroDemo
 * @BelongsPackage: PACKAGE_NAME
 * @Author: yinxiucahun
 * @CreateTime: 2019-05-22 10:32
 * @Description: 测试自定义权限
 */
public class AuthorizerTest {
    //首先通用化登录逻辑
    private void login(String configFile, String username, String password) {
        //1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);
        //2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
    }

    @Test
    public void testIsPermitted(){
        login("classpath:shiro-authorizer.ini", "zhang", "123");
        Subject subject = SecurityUtils.getSubject();
        //判断拥有权限：user:create
        Assert.assertTrue(subject.isPermitted("user1:update"));
        Assert.assertTrue(subject.isPermitted("user2:update"));
        //通过二进制位的方式表示权限
        Assert.assertTrue(subject.isPermitted("+user1+2"));//新增权限
        Assert.assertTrue(subject.isPermitted("+user1+8"));//查看权限
        Assert.assertTrue(subject.isPermitted("+user2+10"));//新增及查看
        Assert.assertFalse(subject.isPermitted("+user1+4"));//没有删除权限
        Assert.assertTrue(subject.isPermitted("menu:view"));// 通过MyRolePermissionResolver 解析得到的权限
    }
}
