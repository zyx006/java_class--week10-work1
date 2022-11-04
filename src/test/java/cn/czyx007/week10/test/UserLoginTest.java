package cn.czyx007.week10.test;

import cn.czyx007.week10.bean.User;
import cn.czyx007.week10.service.UserLogin;
import org.junit.Test;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/3
 */
public class UserLoginTest {
    @Test
    public void testLogin(){
        if (UserLogin.login(new User("admin", "123"))) {
            System.out.println("true");
        } else System.out.println("false");
    }

    @Test
    public void testCheckPassword(){
        if (UserLogin.checkPassword(new User("admin", "123"), "123")){
            System.out.println("true");
        } else System.out.println("false");
    }

    @Test
    public void testUpdateUserDataFile(){
        UserLogin.updateUserDataFile(new User("admin", "123"));
    }

    //testChangePassword
    public static void main(String[] args) {
        UserLogin.changePassword(new User("admin", "123"));
    }
}
