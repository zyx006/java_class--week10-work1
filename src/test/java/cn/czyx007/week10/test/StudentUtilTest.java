package cn.czyx007.week10.test;

import cn.czyx007.week10.bean.Student;
import cn.czyx007.week10.utils.StudentUtil;
import cn.czyx007.week10.utils.TxtUtil;
import org.junit.Test;

import java.util.*;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/3
 */
public class StudentUtilTest {
    @Test
    public void testCheckIfStudentScoreDuplicate() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("1001", "张三", "男", "高数", 95.0));

        Map<String, List<String>> studentMap = new HashMap<>();
        List<String> lessons = new ArrayList<>();
        lessons.add("高数");
        lessons.add("英语");
        studentMap.put("1001", lessons);

        if (!StudentUtil.checkIfStudentScoreDuplicate(studentMap, "1001", "高数")) {
            System.out.println("Duplicated!");
            students.forEach(System.out::println);
        }
    }

    @Test
    public void testSelectAllScoreById(){
        List<Student> students = TxtUtil.loadDataFromTxt();
        StudentUtil.selectAllScoreById("1001", students);
    }

    //testInputDataFromKeyBoard
    public static void main(String[] args) {
        List<Student> students = StudentUtil.inputDataFromKeyBoard();
        students.forEach(System.out::println);
    }
}
