package cn.czyx007.week10.service;

import cn.czyx007.week10.bean.Student;
import cn.czyx007.week10.bean.User;
import cn.czyx007.week10.utils.ExcelUtil;
import cn.czyx007.week10.utils.JsonUtil;
import cn.czyx007.week10.utils.StudentUtil;
import cn.czyx007.week10.utils.TxtUtil;

import java.util.List;
import java.util.Scanner;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/3
 */
public class MainMenu {
    /**
     * 主菜单界面和业务逻辑控制
     * @param user 已成功登录的用户
     */
    public static void menu(User user){
        int opt;
        List<Student> students = null;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("=====学生成绩管理系统=======\n" +
                    "1.从excel文件中加载数据\n" +
                    "2.从文本文件中加载数据\n" +
                    "3.从json文件中加载数据\n" +
                    "4.键盘输入数据\n" +
                    "5.成绩查询\n" +
                    "6.输出到excel文件\n" +
                    "7.输出到纯文本文件\n" +
                    "8.输出到json文件\n" +
                    "9.修改密码\n" +
                    "10.退出\n" +
                    "请输入选项：>>>");
            do {
                opt = checkInput(1, 10);
            } while (opt == -1);

            switch (opt){
                case 1://1.从excel文件中加载数据
                    students = ExcelUtil.loadDataFromExcel();
                    break;
                case 2://2.从文本文件中加载数据
                    students = TxtUtil.loadDataFromTxt();
                    break;
                case 3://3.从json文件中加载数据
                    students = JsonUtil.loadDataFromJson();
                    break;
                case 4://4.键盘输入数据
                    students = StudentUtil.inputDataFromKeyBoard();
                    break;
                case 5://5.成绩查询
                    System.out.println("请输入待查询学生的学号：");
                    String id = new Scanner(System.in).nextLine();
                    StudentUtil.selectAllScoreById(id, students);
                    break;
                case 6://6.输出到excel文件
                    ExcelUtil.exportDataToExcel(students);
                    break;
                case 7://7.输出到纯文本文件
                    TxtUtil.exportDataToTxt(students);
                    break;
                case 8://8.输出到json文件
                    JsonUtil.exportDataToJson(students);
                    break;
                case 9://9.修改密码
                    UserLogin.changePassword(user);
                    break;
                case 10://10.退出
                    while (true) {
                        System.out.println("确认退出系统吗？(Y/N)：");
                        String option = scanner.nextLine();
                        if ("Y".equalsIgnoreCase(option)) {
                            System.exit(0);
                        } else if ("N".equalsIgnoreCase(option)) {
                            break;
                        } else {
                            System.out.println("输入错误！请重新输入！");
                        }
                    }
            }
            System.out.println();
        }
    }

    /**
     * 检查菜单选项输入合法性
     * @param low 选项下界
     * @param high 选项上界
     * @return 合法选项值
     */
    public static int checkInput(int low, int high) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int opt = scanner.nextInt();
            if (opt >= low && opt <= high) {
                return opt;
            }
            System.out.println("输入超限，请输入" + low + "-" + high + "的数字");
        } else {
            System.out.println("输入数据类型错误，请输入" + low + "-" + high + "的数字");
            scanner.next();
        }
        return -1;
    }
}
