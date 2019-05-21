import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @BelongsProject: shiroDemo
 * @BelongsPackage: PACKAGE_NAME
 * @Author: yinxiucahun
 * @CreateTime: 2019-05-21 14:44
 * @Description: 测试用例
 */
public class testHelloWorld {

    @Test
    public void testHelloWorld1() {
        //获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        //得到 SecurityManager 实例 并绑定给 SecurityUtils（全局设置，设置一次即可）
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try{
            //登录（验证身份）
            subject.login(token);
        }catch (AuthenticationException e){
            //身份验证失败
            System.out.println("错误");
        }
        //断言 断言用户已经登陆
        Assert.assertEquals(true,subject.isAuthenticated());
        //退出
        subject.logout();

    }

    @Test
    public void testHelloWorld2() {
        //获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //得到 SecurityManager 实例 并绑定给 SecurityUtils（全局设置，设置一次即可）
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try{
            //登录（验证身份）
            subject.login(token);
        }catch (AuthenticationException e){
            //身份验证失败
            System.out.println("错误");
        }
        //断言 断言用户已经登陆
        Assert.assertEquals(true,subject.isAuthenticated());
        //退出
        subject.logout();
    }

    @Test
    public void testHelloWorld3() {
        //获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
        //得到 SecurityManager 实例 并绑定给 SecurityUtils（全局设置，设置一次即可）
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang","123");

        try{
            //登录（验证身份）
            subject.login(token);
        }catch (AuthenticationException e){
            //身份验证失败
            System.out.println("错误");
        }
        //断言 断言用户已经登陆
        Assert.assertEquals(true,subject.isAuthenticated());
        //退出
        subject.logout();
    }
}
