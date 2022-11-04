package cn.czyx007.week10;

import cn.czyx007.week10.bean.User;
import cn.czyx007.week10.service.MainMenu;
import cn.czyx007.week10.service.UserLogin;

import java.util.Scanner;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/3
 */
public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cnt = 0;
        User user = null;
        while (cnt < 3) {
            System.out.print("请输入用户名：");
            String username = scanner.nextLine();
            System.out.print("请输入密码：");
            String password = scanner.nextLine();
            user = new User(username, password);
            if (UserLogin.login(user)) {
                break;
            } else {
                System.out.println("您输入的用户名或密码不正确");
            }
            cnt++;
        }
        if (cnt == 3){
            System.out.println("尝试超限！程序已退出");
            return;
        }
        MainMenu.menu(user);
    }
}
