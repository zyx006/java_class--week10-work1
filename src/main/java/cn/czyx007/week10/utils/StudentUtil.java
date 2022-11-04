package cn.czyx007.week10.utils;

import cn.czyx007.week10.bean.Student;

import java.io.IOException;
import java.util.*;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/3
 */
public class StudentUtil {
    /**
     * 检查学生课程成绩是否重复
     * @param studentMap 存储各学生的所有课程名称，用于辅助验证特定课程成绩是否重复。
     *                   Key存储学生学号，Value存储学生所有课程名称的List集合
     * @param id 学生学号
     * @param lessonName 特定课程名称
     * @return true:课程成绩未重复  false:课程成绩重复
     */
    public static boolean checkIfStudentScoreDuplicate(Map<String, List<String>> studentMap, String id, String lessonName) {
        if (!(studentMap.containsKey(id) && studentMap.get(id).contains(lessonName))) {
            if (!studentMap.containsKey(id)) {
                List<String> lessons = new ArrayList<>();
                lessons.add(lessonName);
                studentMap.put(id, lessons);
            } else {
                studentMap.get(id).add(lessonName);
            }
            return true;
        }
        return false;
    }

    /**
     * 从键盘依次录入学生成绩信息
     * @return 录入的所有学生成绩信息的List集合
     */
    public static List<Student> inputDataFromKeyBoard() {
        List<Student> students = new ArrayList<>();

        System.out.println("学生成绩信息录入格式：学号，姓名，性别，课程名称，成绩");
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> studentMap = new HashMap<>();
        while (true) {
            System.out.println("请依次输入学生成绩信息(输入end结束)：");
            String str = scanner.nextLine();
            if ("end".equalsIgnoreCase(str)) {
                break;
            }
            try {
                String[] attributes = str.split("[,，]");
                String id = attributes[0];
                String name = attributes[1];
                String gender = attributes[2];
                String lessonName = attributes[3];
                Double score = Double.parseDouble(attributes[4]);

                if (StudentUtil.checkIfStudentScoreDuplicate(studentMap, id, lessonName)) {
                    students.add(new Student(id, name, gender, lessonName, score));
                    System.out.println("增加成功！");
                } else {
                    System.out.println("该学生不能重复录入成绩信息，请重新输入！");
                }
            } catch (Exception e) {
                System.out.println("输入出错！请检查输入格式！");
            }
        }
        try {
            System.out.print("按任意键返回主菜单");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    /**
     * 根据学生学号查询其所有课程成绩
     * @param id 待查询课程成绩的学生学号
     * @param students 所有学生成绩信息的List集合
     */
    public static void selectAllScoreById(String id, List<Student> students) {
        boolean flag = false;
        if (students != null) {
            for (Student student : students) {
                if (student.getId().equals(id)) {
                    flag = true;
                    System.out.println(student.getLessonName() + " " + student.getScore());
                }
            }
        }
        if (!flag) {
            System.out.println("不存在该学生！");
        }

        try {
            System.out.print("按任意键返回主菜单");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
