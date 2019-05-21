import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import sun.security.smartcardio.SunPCSC;

/**
 * @BelongsProject: shiroDemo
 * @BelongsPackage: PACKAGE_NAME
 * @Author: yinxiucahun
 * @CreateTime: 2019-05-21 16:32
 * @Description: 认证策略
 */
public class AuthenticatorTest {
    //首先通用化登录逻辑
    private void login(String configFile) {
        //1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);
        //2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        subject.login(token);
    }

    //测试 AllSuccessfulStrategy 成功：
    @Test
    public void testAllSuccessfulStrategyWithSuccess() {
        login("classpath:shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，其包含了 Realm 验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
    }

    //测试 AllSuccessfulStrategy 失败
    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail() {
        //此处认证失败
        try {
            login("classpath:shiro-authenticator-all-fail.ini");
        }catch (UnknownAccountException e){
            System.out.println("错误");
        }

        Subject subject = SecurityUtils.getSubject();

    }

}
