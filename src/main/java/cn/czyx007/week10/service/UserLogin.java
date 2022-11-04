package cn.czyx007.week10.service;

import cn.czyx007.week10.bean.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/3
 */
public class UserLogin {
    /**
     * 登录
     * @param user 待登录的User对象，属性为输入的用户名和密码
     * @return true:登录成功  false: 登录失败
     */
    public static boolean login(User user){
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/users.csv"))) {
            String aLine;
            while ((aLine=br.readLine()) != null){
                String[] nameAndPwd = aLine.split(" ");
                if(nameAndPwd[0].equals(user.getUsername()) && nameAndPwd[1].equals(user.getPassword())){
                    return true;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 验证输入的 原密码 是否正确
     * @param user 待验证密码的User对象
     * @param oldPassword 输入的原密码
     * @return true:输入的原密码正确  false:输入的原密码错误
     */
    public static boolean checkPassword(User user, String oldPassword){
        return user.getPassword().equals(oldPassword);
    }

    /**
     * 修改密码并写回到users.csv文件中
     * @param user 待修改密码的User对象
     */
    public static void changePassword(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入原密码：");
        String oldPassword = scanner.nextLine();
        if (!UserLogin.checkPassword(user, oldPassword)) {
            System.out.println("密码输入错误！");
        } else {
            System.out.println("请输入新密码：");
            String newPassword = scanner.nextLine();
            user.setPassword(newPassword);

            updateUserDataFile(user);
            System.out.println("密码已修改！");
        }
        try {
            System.out.print("按任意键返回主菜单");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新用户数据文件users.csv中的某用户的密码
     * @param user 待更新密码的User对象
     */
    public static void updateUserDataFile(User user){
        //读取原有用户数据
        List<User> users = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/users.csv"))) {
            String aLine;
            while ((aLine= br.readLine()) != null){
                String[] nameAndPwd = aLine.split(" ");
                users.add(new User(nameAndPwd[0], nameAndPwd[1]));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        //修改指定用户密码
        for (User usr : users) {
            if(usr.getUsername().equals(user.getUsername())){
                usr.setPassword(user.getPassword());
            }
        }

        //将修改后的数据写回到文件中
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/users.csv"))) {
            for (User usr : users) {
                bw.write(usr.getUsername() + " " + usr.getPassword() + "\n");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
