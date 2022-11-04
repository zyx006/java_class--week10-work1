package cn.czyx007.week10.utils;

import cn.czyx007.week10.bean.Student;

import java.io.*;
import java.util.*;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/3
 */
public class TxtUtil {
    /**
     * 从txt文件中加载数据
     * @return 所有不重复的学生数据的List集合
     */
    public static List<Student> loadDataFromTxt() {
        List<Student> students = new ArrayList<>();

        Map<String, List<String>> studentMap = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/student.txt"))) {
            int cnt = 0;
            String aLine;
            br.readLine();
            while ((aLine=br.readLine()) != null){
                String[] attributes = aLine.split("[ \t]");
                String id = attributes[0];
                String name = attributes[1];
                String gender = attributes[2];
                String lessonName = attributes[3];
                Double score = Double.parseDouble(attributes[4]);

                if(StudentUtil.checkIfStudentScoreDuplicate(studentMap, id, lessonName)){
                    students.add(new Student(id, name, gender, lessonName, score));
                    cnt++;
                }
            }

            System.out.println("成功导入 " + cnt + " 条学生数据");
            System.out.print("按任意键返回主菜单");
            System.in.read();
        } catch (Exception e) {
            System.out.println("数据导入失败！");
            e.printStackTrace();
        }
        return students;
    }

    /**
     * 导出学生成绩信息到txt文件中
     * @param students 待导出成绩信息的学生数据的List集合
     */
    public static void exportDataToTxt(List<Student> students) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/score.txt"))) {
            bw.write("学号\t姓名\t性别\t总分\t平均分\n");

            int studentCnt = 0;
            Map<String, Boolean> isWritten = new HashMap<>();
            for (int i = 0; i < students.size(); i++) {
                if(isWritten.containsKey(students.get(i).getId())){
                    continue;
                }

                bw.write(students.get(i).getId() + "\t" + students.get(i).getName() + "\t" + students.get(i).getGender() + "\t");

                int lessonCnt=0;
                double allScore=0;
                for (Student stu : students) {
                    if(stu.getId().equals(students.get(i).getId())){
                        lessonCnt++;
                        allScore += stu.getScore();
                    }
                }
                bw.write(allScore + "\t" + allScore/lessonCnt + "\n");
                studentCnt++;

                isWritten.put(students.get(i).getId(), true);
            }
            System.out.println("成功导出 " + studentCnt + " 条学生成绩信息到 txt 文件中");
            System.out.print("按任意键返回主菜单");
            System.in.read();
        } catch (Exception e) {
            System.out.println("数据导出失败！");
            e.printStackTrace();
        }
    }
}
